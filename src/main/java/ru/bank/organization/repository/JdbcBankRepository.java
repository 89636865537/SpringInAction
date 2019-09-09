package ru.bank.organization.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;
import ru.bank.organization.entity.Bank;

import javax.sql.DataSource;
import java.util.Optional;


@Repository("JdbcBankRepository")
public class JdbcBankRepository extends NamedParameterJdbcDaoSupport implements BankRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcBankRepository.class);


    DataSource dataSource;
    TransactionTemplate transactionTemplate;


    @Autowired
    public JdbcBankRepository(@Qualifier("dataSourceJdbc") DataSource dataSource,
                              @Qualifier("transactionTemplate") TransactionTemplate transactionTemplate) {
        this.dataSource = dataSource;
        this.transactionTemplate = transactionTemplate;
        super.setDataSource(dataSource);
    }


    @Override
    public Optional<Long> saveBank(Optional<Bank> bank) {


        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("bankName", bank.map(Bank::getName).orElseThrow(() -> {
            log.info("input bank object is null");
            return new IllegalArgumentException();
        }));
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO BANK ( NAME) VALUES( :bankName)";


       Long result = Optional.of(super.getNamedParameterJdbcTemplate().update(sql, namedParameters)).map(Integer::longValue).orElseThrow(() -> {
            log.info("cannot save Bank");
            return new IllegalArgumentException();
        });

        return Optional.of(result);
    }

    @Override
    public Optional<Bank> getBankById(Long bankId) {
        String sql = "SELECT * FROM BANK WHERE ID = :bankId";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("bankId", bankId);
        Optional<Bank> optionalBank = Optional.of(super.getNamedParameterJdbcTemplate().queryForObject(sql, namedParameters, (resultSet, i) -> {
            Bank bank = new Bank();
            bank.setName(resultSet.getString("name"));
            bank.setId(Long.parseLong(resultSet.getString("id")));
            return bank;
        }));
        return optionalBank;
    }


}
