package es.velazquez.proyectointegradoapi.controller;

import es.velazquez.proyectointegradoapi.dto.JWTDTO;
import es.velazquez.proyectointegradoapi.model.LoginUser;
import es.velazquez.proyectointegradoapi.model.User;
import es.velazquez.proyectointegradoapi.security.jwt.JwtTokenProvider;
import es.velazquez.proyectointegradoapi.security.UserPrincipal;
import es.velazquez.proyectointegradoapi.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author pablorey
 * @since 25-05-2024
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * Método para registrar nuevos {@link User} en el sistema.
     * @param user Objeto de registro de usuario con email, contraseña y rol.
     * @return El usuario registrado si es {@link HttpStatus#CREATED}, mensaje de error {@link HttpStatus#BAD_REQUEST}
     * si ya existe ese email.
     * @see UserServiceImpl#saveUser(User)
     * @see User
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("ERROR: el correo electrónico ya está en uso.", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);

        log.info("Usuario creado: " + user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Método para el inicio de sesión de los usuarios en el sistema. El autenticador realiza un filtro y genera el JWT
     * si las credenciales son correctas.
     * @param loginUser Objeto de inicio de sesión del usuario con email y contraseña.
     * @return El JSON Web Token como cadena de texto en caso de que las credenciales sean correctas
     * ({@link HttpStatus#OK}), mensaje de error {@link HttpStatus#BAD_REQUEST} en caso contrario;
     * {@link HttpStatus#INTERNAL_SERVER_ERROR} si hay otro error del servidor.
     * @apiNote Se introduce sólo un objeto {@link LoginUser} que no tiene el campo {@link User#getRole()} para que sea
     * compatible con la implementación de {@link UserPrincipal}.
     * @see LoginUser
     * @see JwtTokenProvider#generateToken(Authentication)
     * @see JWTDTO
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUser loginUser) {
        log.info("loginUser: " + loginUser.toString());
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt =jwtTokenProvider.generateToken(authentication);
            return new ResponseEntity<>(new JWTDTO(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("ERROR: las credenciales no son válidas.", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR: error interno al procesar la solicitud.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
