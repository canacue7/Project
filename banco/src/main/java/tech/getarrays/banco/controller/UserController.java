package tech.getarrays.banco.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.ApiOperation;
import tech.getarrays.banco.Model.Respuesta;
import tech.getarrays.banco.entity.UserEntity;
import tech.getarrays.banco.service.UserService;
import tech.getarrays.banco.util.JwtUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
	

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Login with userName and password.
	 * 
	 * @param user
	 * @return
	 */
	
	@ApiOperation(value = "Login with userName and password", response = ResponseEntity.class)
	@PostMapping("/auth")
	public ResponseEntity<Respuesta<UserEntity>> login(@RequestBody UserEntity user) {
		
		Respuesta<UserEntity> output = new Respuesta<>();
		String msg = null;
		HttpStatus status = null;

		try {
			
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
					);
			user.setJwt(jwtUtils.generateToken(user.getUserName()));
		
	
			
			
			user.setPassword(null);
			msg = "0 - Succesfully logged in " + user.getUserName() + ".";
			
			output.setMessa(msg);
			output.setDone(true);
			output.setDato(user);
			status = HttpStatus.OK;
			
		} catch (AuthenticationException e) {
			
			msg = "Incorrect user or password.";
			status = HttpStatus.FORBIDDEN;
			output.setMessa(msg + e);
			output.setDone(false);
			System.out.print(e);
			
			String log = msg + e.getLocalizedMessage();
			logger.error(log); 
			
			
//		}catch (AuthenticationException e) {
//
//            msg = "Usuario o clave incorrectos.";
//            status = HttpStatus.FORBIDDEN;
//            output.setMessa(msg + e);
//            output.setDone(false);
//
//            String log = msg + e.getLocalizedMessage();           
//            logger.error(log);   	
		} catch (Exception e) {
			
			msg = "Looks like something went wrong. contact support";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			output.setMessa(msg);
			output.setDone(false);
			
		}

		return new ResponseEntity<>(output, status);
	}
	
	/**
	 * List users.
	 * 
	 * @return @List<UserVO>
	 */
	@ApiOperation(value = "List users.", response = ResponseEntity.class)
	@GetMapping
	public ResponseEntity<Respuesta<List<UserEntity>>> get() {
		
		Respuesta<List<UserEntity>> output = new Respuesta<>();
		HttpStatus status = null;
		List<UserEntity> data = null; 

		try {

			data = userService.get();
			String msg = "It found " + data.size() + " users.";
			
			output.setMessa(msg);
			output.setDone(true);
			output.setDato(data);
			status = HttpStatus.OK;
			
		} catch (Exception e) {
			
			String msg = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			output.setMessa(msg);
			output.setDone(false);
			
			String log = "End point GET/user has failed. " + e.getLocalizedMessage();			
			logger.error(log);
		}

		return new ResponseEntity<>(output, status);
	}
	
	@ApiOperation(value = "Add users.", response = ResponseEntity.class)
	@PostMapping
	public ResponseEntity<Respuesta<UserEntity>> save(@RequestBody UserEntity user) {
		
		Respuesta<UserEntity> output = new Respuesta<>();
		HttpStatus status = null;
		UserEntity data = null; 
		String mensaje = null;
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		try {

			data = userService.addUser(user);
			mensaje = "0 - User created";
			
			output.setMessa(mensaje);
			output.setDone(true);
			output.setDato(data);
			status = HttpStatus.OK;
			
		} catch (Exception e) {
			
			mensaje = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			output.setMessa(mensaje);
			output.setDone(false);
			
		}

		return new ResponseEntity<>(output, status);
	}
	
	/**
	 * Delete user.
	 * 
	 * @return @List<UserVO>
	 */
	@ApiOperation(value = "Delete user.", response = ResponseEntity.class)
	@DeleteMapping("/{userName}")
	public ResponseEntity<Respuesta<String>> delete(@PathVariable String userName) {
		
		Respuesta<String> output = new Respuesta<>();
		HttpStatus status = null;

		try {

			userService.delete(userName);
			String msg = "It delete by user " + userName + ".";
			
			output.setMessa(msg);
			output.setDone(true);
			output.setDato(userName);
			status = HttpStatus.OK;
			
		} catch (Exception e) {
			
			String msg = "Something has failed. Please contact suuport.";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			output.setMessa(msg);
			output.setDone(false);
			
			String log = "End point GET/user has failed. " + e.getLocalizedMessage();			
			logger.error(log);
		}

		return new ResponseEntity<>(output, status);
	}
}
