package br.com.sh.appproposta.mapper;

import br.com.sh.appproposta.model.UsuarioModel;
import br.com.sh.appproposta.model.form.UsuarioForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UsuarioMapper {


//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "nome", source = "usuarioForm.nome")
//    @Mapping(target = "sobrenome", source = "usuarioForm.sobrenome")
//    @Mapping(target = "telefone", source = "usuarioForm.telefone")
//    @Mapping(target = "cpf", source = "usuarioForm.cpf")
//    @Mapping(target = "renda", source = "usuarioForm.renda")
    UsuarioModel convertoDtoToModel(UsuarioForm usuarioForm);
}
