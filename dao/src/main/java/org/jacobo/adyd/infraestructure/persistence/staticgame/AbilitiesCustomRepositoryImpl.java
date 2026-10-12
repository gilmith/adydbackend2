package org.jacobo.adyd.infraestructure.persistence.staticgame;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jacobo.adyd.domain.model.staticgamevalues.BaseStaticPointModel;
import org.jacobo.adyd.domain.repository.staticgame.AbilitiesCustomRepository;
import org.jacobo.adyd.domain.service.staticgame.StaticAbilitiesEnum;
import org.jacobo.adyd.infraestructure.entities.staticgame.StaticTableBasePointEntity;
import org.jacobo.adyd.infraestructure.mapper.staticgame.StaticAbilityMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AbilitiesCustomRepositoryImpl implements AbilitiesCustomRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final StaticAbilityMapper staticAbilityMapper;


    @Override
    public <T extends BaseStaticPointModel> T findByBasePoint(Long id, StaticAbilitiesEnum abilitiesEnum) {
        val enumEntity = staticAbilityMapper.toStaticAbilitiesEntityEnum(abilitiesEnum);
        val query = enumEntity.getQuery();
        val map = new MapSqlParameterSource();
        map.addValue("id", id);
        val rowMapper = BeanPropertyRowMapper.newInstance(enumEntity.getClazz());
        val value = this.namedParameterJdbcTemplate.queryForObject(query, map, rowMapper);
        return staticAbilityMapper.toDomain((StaticTableBasePointEntity) value);
    }
}
