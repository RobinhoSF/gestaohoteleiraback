package test;

import controller.ClienteController;
import controller.ExemploController;
import model.Cliente;

import java.time.LocalDate;

public class ClienteTest {
    public String testeCadastro(Long id,String nomeCompleto,
                            String dataNascimento,
                            String documento,
                            String pais,
                            String estado,
                            String cidade,
                            Boolean fidelidade,
                            String observacao) {
        ClienteController clienteController = new ClienteController();
        String resposta = clienteController.cadastrar(id,nomeCompleto,
                dataNascimento,
                documento,
                pais,
                estado,
                cidade,
                fidelidade,
                observacao);
        return resposta;
    }

    public String testeListagem() {
        ClienteController clienteController = new ClienteController();
        String resposta = clienteController.listar();
        return resposta;
    }

    public String testeAlteracao(Long id,
                                 String nomeCompleto,
                                 LocalDate dataNascimento,
                                 String documento,
                                 String pais,
                                 String estado,
                                 String cidade,
                                 Boolean fidelidade,
                                 String observacao) {
        ClienteController clienteController = new ClienteController();
        String resposta = clienteController.alterar(id,
                nomeCompleto,
                dataNascimento,
                documento,
                pais,
                estado,
                cidade,
                fidelidade,
                observacao);
        return resposta;
    }

    public String testeExclusao(Long id) {
        ClienteController clienteController = new ClienteController();
        String resposta = clienteController.excluir(id);
        return resposta;
    }
}
