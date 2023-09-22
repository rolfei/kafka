package com.rolfei.realestate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;

@ConfigurationProperties(prefix = "confluent-cloud-config")
public class AppConfigProperties {
    private String message;
    private String bootstrapServers;

    private String schemaRegistryUrl;

    private String securityProtocol;

    private String saslMechanism;

    private String clientDnsLookup;

    private String sessionTimeoutMs;

    private String basicAuthCredentialsSource;

    public String getBasicAuthUserInfo() {
        return basicAuthUserInfo;
    }

    public void setBasicAuthUserInfo(String basicAuthUserInfo) {
        this.basicAuthUserInfo = basicAuthUserInfo;
    }

    private String basicAuthUserInfo;

    public String getBasicAuthCredentialsSource() {
        return basicAuthCredentialsSource;
    }

    public void setBasicAuthCredentialsSource(String basicAuthCredentialsSource) {
        this.basicAuthCredentialsSource = basicAuthCredentialsSource;
    }



    public String getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(String sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }



    public String getClientDnsLookup() {
        return clientDnsLookup;
    }

    public void setClientDnsLookup(String clientDnsLookup) {
        this.clientDnsLookup = clientDnsLookup;
    }



    public String getSaslMechanism() {
        return saslMechanism;
    }

    public void setSaslMechanism(String saslMechanism) {
        this.saslMechanism = saslMechanism;
    }



    public String getSaslJaasConfig() {
        return saslJaasConfig;
    }

    public void setSaslJaasConfig(String saslJaasConfig) {
        this.saslJaasConfig = saslJaasConfig;
    }

    private String saslJaasConfig;

    public void setSecurityProtocol(String securityProtocol) {
        this.securityProtocol = securityProtocol;
    }



    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }



    public String getBootstrapServers() {
        return bootstrapServers;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSchemaRegistryUrl() {
        return schemaRegistryUrl;
    }

    public void setSchemaRegistryUrl(String schemaRegistryUrl) {
        this.schemaRegistryUrl = schemaRegistryUrl;
    }

    public String getSecurityProtocol() {
        return this.securityProtocol;
    }
}