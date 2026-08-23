package org.ljh.dependencies.group.truelicense;

import de.schlichtherle.license.AbstractKeyStoreParam;

import java.io.*;

/**
 * 自定义密钥库参数
 *
 * @author ljh
 * @since 2024/1/18 9:02
 */
public class CustomKeyStoreParam extends AbstractKeyStoreParam {

    private final String storePath;
    private final String alias;
    private final String storePwd;
    private final String keyPwd;

    public CustomKeyStoreParam(Class clazz, String resource, String alias, String storePwd, String keyPwd) {
        super(clazz, resource);
        this.storePath = resource;
        this.alias = alias;
        this.storePwd = storePwd;
        this.keyPwd = keyPwd;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getStorePwd() {
        return storePwd;
    }

    @Override
    public String getKeyPwd() {
        return keyPwd;
    }

    @Override
    public InputStream getStream() throws IOException {
        return new FileInputStream(storePath);
    }
}
