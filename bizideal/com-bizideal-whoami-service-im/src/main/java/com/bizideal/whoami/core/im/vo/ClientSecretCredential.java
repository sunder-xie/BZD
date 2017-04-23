package com.bizideal.whoami.core.im.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.node.ObjectNode;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.bizideal.whoami.core.im.common.HTTPMethod;
import com.bizideal.whoami.core.im.common.Roles;
import com.bizideal.whoami.core.im.utils.ResteasyUtils;


/**
 * @ClassName ClientSecretCredential
 * @Description TODO(客户秘密凭证)
 * @Author Zj.Qu
 * @Date 2017-01-04 13:42:53
 */
public class ClientSecretCredential extends Credential {

	//https://a1.easemob.com/sephiroth/meeting/token
	private static ResteasyWebTarget CLIENT_TOKEN_TARGET = null;
    private static final String  GRANT_TYPE = "client_credentials";

	public ClientSecretCredential(String clientID, String clientSecret, String role) {
		super(clientID, clientSecret);

		if (role.equals(Roles.USER_ROLE_APPADMIN)) {
			CLIENT_TOKEN_TARGET =  EndPoints.TOKEN_APP_TARGET;
		}
	}

	@Override
	protected GrantType getGrantType() {
		return GrantType.CLIENT_CREDENTIALS;
	}

	@Override
	protected ResteasyWebTarget getTokenRequestTarget() {
		return CLIENT_TOKEN_TARGET;
	}

	@Override
	public Token getToken() {

		if (null == token || token.isExpired()) {
			try {
				ObjectNode objectNode = factory.objectNode();
				objectNode.put("grant_type", GRANT_TYPE);
				objectNode.put("client_id", tokenKey1);
				objectNode.put("client_secret", tokenKey2);
				List<NameValuePair> headers = new ArrayList<NameValuePair>();
				headers.add(new BasicNameValuePair("Content-Type", "application/json"));

				ObjectNode tokenRequest = ResteasyUtils.sendRequest(getTokenRequestTarget(), objectNode, null,
						HTTPMethod.METHOD_POST, headers);

				if (null != tokenRequest.get("error")) {
                    throw new RuntimeException("Some errors occurred while fetching a token by " +
                            "grant_type[" + GRANT_TYPE + "] client_id[" + tokenKey1 + "]" +
                            " and client_secret[" + tokenKey2 + "] .");
				}

				String accessToken = tokenRequest.get("access_token").asText();

				Long expiredAt = System.currentTimeMillis() + tokenRequest.get("expires_in").asLong() * 1000;

				token = new Token(accessToken, expiredAt);
			} catch (Exception e) {
                throw new RuntimeException("Some errors occurred while fetching a token by " +
                        "grant_type[" + GRANT_TYPE + "] client_id[" + tokenKey1 + "]" +
                        " and client_secret[" + tokenKey2 + "] .");
            }
		}

		return token;
	}
}
