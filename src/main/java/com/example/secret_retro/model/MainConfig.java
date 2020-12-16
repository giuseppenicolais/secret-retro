package com.example.secret_retro.model;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class MainConfig {

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI("postgres://gdmvmkhwvrpstu:72b6d878fd00df1c0ab8993161ecd1460befa45d1f628d7e3165a5f20c0ac951@ec2-54-170-123-247.eu-west-1.compute.amazonaws.com:5432/d7nvhmvqmnhkh6");

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
}
