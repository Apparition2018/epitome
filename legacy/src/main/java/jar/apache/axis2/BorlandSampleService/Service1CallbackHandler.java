/**
 * Service1CallbackHandler.java
 *
 * <p>This file was auto-generated from WSDL by the Apache Axis2 version: 1.8.2 Built on : Jul 13,
 * 2022 (06:38:03 EDT)
 */
package jar.apache.axis2.BorlandSampleService;

/**
 * Service1CallbackHandler Callback class, Users can extend this class and implement their own
 * receiveResult and receiveError methods.
 */
public abstract class Service1CallbackHandler {

  protected Object clientData;

  /**
   * User can pass in any object that needs to be accessed once the NonBlocking Web service call is
   * finished and appropriate method of this CallBack is called.
   *
   * @param clientData Object mechanism by which the user can pass in user data that will be
   *     avilable at the time this callback is called.
   */
  public Service1CallbackHandler(Object clientData) {
    this.clientData = clientData;
  }

  /** Please use this constructor if you don't want to set any clientData */
  public Service1CallbackHandler() {
    this.clientData = null;
  }

  /** Get the client data */
  public Object getClientData() {
    return clientData;
  }

  /**
   * auto generated Axis2 call back method for getUserObject method override this method for
   * handling normal response from getUserObject operation
   */
  public void receiveResultgetUserObject(Service1Stub.GetUserObjectResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * getUserObject operation
   */
  public void receiveErrorgetUserObject(Exception e) {}

  /**
   * auto generated Axis2 call back method for a_ReadMeFirst method override this method for
   * handling normal response from a_ReadMeFirst operation
   */
  public void receiveResulta_ReadMeFirst(Service1Stub.A_ReadMeFirstResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * a_ReadMeFirst operation
   */
  public void receiveErrora_ReadMeFirst(Exception e) {}

  /**
   * auto generated Axis2 call back method for echoStringArray method override this method for
   * handling normal response from echoStringArray operation
   */
  public void receiveResultechoStringArray(
      Service1Stub.EchoStringArrayResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * echoStringArray operation
   */
  public void receiveErrorechoStringArray(Exception e) {}

  /**
   * auto generated Axis2 call back method for getUserInformation method override this method for
   * handling normal response from getUserInformation operation
   */
  public void receiveResultgetUserInformation(
      Service1Stub.GetUserInformationResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * getUserInformation operation
   */
  public void receiveErrorgetUserInformation(Exception e) {}

  /**
   * auto generated Axis2 call back method for getNowAsShortString method override this method for
   * handling normal response from getNowAsShortString operation
   */
  public void receiveResultgetNowAsShortString(
      Service1Stub.GetNowAsShortStringResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * getNowAsShortString operation
   */
  public void receiveErrorgetNowAsShortString(Exception e) {}

  /**
   * auto generated Axis2 call back method for setUserObject method override this method for
   * handling normal response from setUserObject operation
   */
  public void receiveResultsetUserObject(Service1Stub.SetUserObjectResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * setUserObject operation
   */
  public void receiveErrorsetUserObject(Exception e) {}

  /**
   * auto generated Axis2 call back method for getRandomNumber method override this method for
   * handling normal response from getRandomNumber operation
   */
  public void receiveResultgetRandomNumber(
      Service1Stub.GetRandomNumberResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * getRandomNumber operation
   */
  public void receiveErrorgetRandomNumber(Exception e) {}

  /**
   * auto generated Axis2 call back method for echoFloat method override this method for handling
   * normal response from echoFloat operation
   */
  public void receiveResultechoFloat(Service1Stub.EchoFloatResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * echoFloat operation
   */
  public void receiveErrorechoFloat(Exception e) {}

  /**
   * auto generated Axis2 call back method for increaseUserAge method override this method for
   * handling normal response from increaseUserAge operation
   */
  public void receiveResultincreaseUserAge(
      Service1Stub.IncreaseUserAgeResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * increaseUserAge operation
   */
  public void receiveErrorincreaseUserAge(Exception e) {}

  /**
   * auto generated Axis2 call back method for getNowAsDate method override this method for handling
   * normal response from getNowAsDate operation
   */
  public void receiveResultgetNowAsDate(Service1Stub.GetNowAsDateResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * getNowAsDate operation
   */
  public void receiveErrorgetNowAsDate(Exception e) {}

  /**
   * auto generated Axis2 call back method for echoString method override this method for handling
   * normal response from echoString operation
   */
  public void receiveResultechoString(Service1Stub.EchoStringResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * echoString operation
   */
  public void receiveErrorechoString(Exception e) {}

  /**
   * auto generated Axis2 call back method for echoObjectArray method override this method for
   * handling normal response from echoObjectArray operation
   */
  public void receiveResultechoObjectArray(
      Service1Stub.EchoObjectArrayResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * echoObjectArray operation
   */
  public void receiveErrorechoObjectArray(Exception e) {}

  /**
   * auto generated Axis2 call back method for setUserInformation method override this method for
   * handling normal response from setUserInformation operation
   */
  public void receiveResultsetUserInformation(
      Service1Stub.SetUserInformationResponse result) {}

  /**
   * auto generated Axis2 Error handler override this method for handling error response from
   * setUserInformation operation
   */
  public void receiveErrorsetUserInformation(Exception e) {}
}
