package com.mackenzie.e_commerce.service;

import com.mackenzie.e_commerce.dto.ClienteDTO;
import com.mackenzie.e_commerce.dto.LoginRequestDTO;
import com.mackenzie.e_commerce.dto.LoginResponseDTO;
import com.mackenzie.e_commerce.dto.RegistroRequestDTO;
import com.mackenzie.e_commerce.enums.Role;
import com.mackenzie.e_commerce.model.Cliente;
import com.mackenzie.e_commerce.repository.ClienteRepository;
import com.mackenzie.e_commerce.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    public ClienteDTO registrar(RegistroRequestDTO dto) {
        if (clienteRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        Cliente novoCliente = new Cliente();
        novoCliente.setNomeCompleto(dto.getNomeCompleto());
        novoCliente.setEmail(dto.getEmail());
        novoCliente.setWhatsapp(dto.getWhatsapp());
        novoCliente.setSenha(passwordEncoder.encode(dto.getSenha()));
        novoCliente.setRole(Role.ROLE_CLIENTE);

        Cliente clienteSalvo = clienteRepository.save(novoCliente);

        return new ClienteDTO(clienteSalvo);
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {

        var authRequest = new UsernamePasswordAuthenticationToken(
                dto.getEmail(), dto.getSenha());


        Authentication autenticacao = authenticationManager.authenticate(authRequest);

        Cliente clienteLogado = (Cliente) autenticacao.getPrincipal();

        String token = jwtTokenService.gerarToken(clienteLogado);

        return new LoginResponseDTO(token);
    }
}