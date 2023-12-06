package controller;

import dao.ClienteDAO;
import dao.ExemploDAO;
import model.Cliente;
import model.Exemplo;
import util.GeralUtil;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClienteController {
    public String cadastrar(Long id,String nomeCompleto,
                            String dataNascimento,
                            String documento,
                            String pais,
                            String estado,
                            String cidade,
                            Boolean fidelidade,
                            String observacao) {
        if(nomeCompleto == null || nomeCompleto.trim().equals("")) {
            return "[ERRO] Campo nome completo é obrigatório. Cliente não cadastrado!";
        }
        if(dataNascimento == null || dataNascimento.equals("")) {
            return "[ERRO] Campo data nascimento é obrigatório. Cliente não cadastrado!";
        }
        if(documento == null || documento.trim().equals("")) {
            return "[ERRO] Campo documento é obrigatório. Cliente não cadastrado!";
        }



        Cliente cliente = new Cliente(id,nomeCompleto,
                GeralUtil.formatarStringParaLocalDate(dataNascimento),
                documento,
                pais,
                estado,
                cidade,
                fidelidade,
                observacao);
        ClienteDAO clienteDAO = new ClienteDAO();

        if(clienteDAO.inserir(cliente)) {
            return "[OK] Cliente cadastrado com sucesso!";
        } else {
            return "[ERRO] Erro desconhecido. Cliente não cadastrado!";
        }
    }

    public String listar() {
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> lista = clienteDAO.selecionar();

        String conteudo = "";
        for (Cliente cliente : lista) {
            conteudo += cliente + "\n";
        }

        return conteudo;
    }

    public String alterar(Long id,
                          String nomeCompleto,
                          LocalDate dataNascimento,
                          String documento,
                          String pais,
                          String estado,
                          String cidade,
                          Boolean fidelidade,
                          String observacao) {
        ClienteDAO clienteDAO = new ClienteDAO();
        if(id == null) {
            return "[ERRO] ID não pode ser nulo. Cliente não alterado!";
        }
        if(clienteDAO.selecionarPorId(id) == null) {
            return "[ERRO] Não encontrado. Cliente não alterado!";
        }
        if(nomeCompleto == null || nomeCompleto.trim().equals("")) {
            return "[ERRO] Campo nome completo é obrigatório. Cliente não cadastrado!";
        }
        if(dataNascimento == null || dataNascimento.equals("")) {
            return "[ERRO] Campo data nascimento é obrigatório. Cliente não cadastrado!";
        }
        if(documento == null || documento.trim().equals("")) {
            return "[ERRO] Campo documento é obrigatório. Cliente não cadastrado!";
        }

        Cliente cliente = new Cliente(
                id,
                nomeCompleto,
                dataNascimento,
                documento,
                pais,
                estado,
                cidade,
                fidelidade,
                observacao);
        if(clienteDAO.atualizar(cliente)) {
            return "[OK] Cliente alterado com sucesso!";
        } else {
            return "[ERRO] Erro desconhecido. Cliente não alterado!";
        }
    }

    public String excluir(Long id) {
        ClienteDAO clienteDAO = new ClienteDAO();

        if(id == null) {
            return "[ERRO] ID não pode ser nulo. Cliente não excluído!";
        }
        if(clienteDAO.selecionarPorId(id) == null) {
            return "[ERRO] Não encontrado. Cliente não excluído!";
        }

        if(clienteDAO.deletar(id)) {
            return "[OK] Cliente excluído com sucesso!";
        } else {
            return "[ERRO] Erro desconhecido. Cliente não excluído!";
        }
    }
}
