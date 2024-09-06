/**
 * ITokenServiceCallbackHandler.java
 *
 * <p>This file was auto-generated from WSDL by the Apache Axis2 version: 1.8.2 Built on : Jul 13,
 * 2022 (06:38:03 EDT)
 */
package jar.apache.axis2.tokenService;

/**
 * ITokenServiceCallbackHandler Callback class, Users can extend this class and implement their own
 * receiveResult and receiveError methods.
 */
public abstract class ITokenServiceCallbackHandler {

  protected Object clientData;

  /**
   * User can pass in any object that needs to be accessed once the NonBlocking Web service call is
   * finished and appropriate method of this CallBack is called.
   *
   * @param clientData Object mechanism by which the user can pass in user data that will be
   *     avilable at the time this callback is called.
   */
  public ITokenServiceCallbackHandler(Object clientData) {
    this.clientData = clientData;
  }

  /** Please use this constructor if you don't want to set any clientData */
  public ITokenServiceCallbackHandler() {
    this.clientData = null;
  }

  /** Get the client data */
  public Object getClientData() {
    return clientData;
  }

  /**
   * auto generated Axis2 call back method for getUploadingToken method override this method for
   * handling normal response from getUploadingToken operation
   */
  public void receiveResultgetUploadingToken(
      ITokenServiceStub.GetUploadingTokenResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * getUploadingToken operation
   */
  public void receiveErrorgetUploadingToken(Exception e) {}

  /**
   * auto generated Axis2 call back method for getToken method override this method for handling
   * normal response from getToken operation
   */
  public void receiveResultgetToken(ITokenServiceStub.GetTokenResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * getToken operation
   */
  public void receiveErrorgetToken(Exception e) {}

  /**
   * auto generated Axis2 call back method for getSafeToken method override this method for handling
   * normal response from getSafeToken operation
   */
  public void receiveResultgetSafeToken(ITokenServiceStub.GetSafeTokenResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * getSafeToken operation
   */
  public void receiveErrorgetSafeToken(Exception e) {}
}
