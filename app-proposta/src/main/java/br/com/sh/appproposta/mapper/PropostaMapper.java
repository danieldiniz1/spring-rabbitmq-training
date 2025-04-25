package br.com.sh.appproposta.mapper;

import br.com.sh.appproposta.model.PropostaModel;
import br.com.sh.appproposta.model.dto.PropostaDTO;
import br.com.sh.appproposta.model.form.PropostaForrm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = UsuarioMapper.class)
public interface PropostaMapper {

    PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);

//    @Mapping(target = "usuario", source = "propostaForrm.usuario")
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "aprovada", ignore = true)
//    @Mapping(target = "integrada", ignore = true)
//    @Mapping(target = "observacao", ignore = true)
    PropostaModel convertDtoToModel(PropostaForrm propostaForrm);

//    @Mapping(target = "nome", source = "propostaModel.usuario.nome")
//    @Mapping(target = "sobrenome" , source = "propostaModel.usuario.sobrenome")
//    @Mapping(target = "telefone", source = "propostaModel.usuario.telefone")
//    @Mapping(target = "cpf", source = "propostaModel.usuario.cpf")
//    @Mapping(target = "renda", source = "propostaModel.usuario.renda")
//    @Mapping(target = "aprovado", source = "propostaModel.aprovada")
    PropostaDTO convertModelToDto(PropostaModel propostaModel);
}
