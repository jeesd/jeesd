package com.jeesd.auth.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

public class JeesdClientDetailService extends JdbcClientDetailsService {

    /**
     * client表字段
     */
    private static final String CLIENT_FIELDS = "client_id, client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * client查询语句
     */
    public static final String BASE_FIND_STATEMENT = "SELECT " + CLIENT_FIELDS
		    + " FROM sys_oauth_client_details";

    /**
     * 默认的查询语句
     */
    public static final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    /**
     * 按条件client_id 查询
     */
    public static final String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

    public JeesdClientDetailService(DataSource dataSource) {
        super(dataSource);
        this.setFindClientDetailsSql(DEFAULT_FIND_STATEMENT);
        this.setSelectClientDetailsSql(DEFAULT_SELECT_STATEMENT);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return super.loadClientByClientId(clientId);
    }
}
