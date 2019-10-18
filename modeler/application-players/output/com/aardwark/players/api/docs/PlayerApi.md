# PlayerApi

All URIs are relative to *http://1.0*

Method | HTTP request | Description
------------- | ------------- | -------------
[**activePlayerListGet**](PlayerApi.md#activePlayerListGet) | **GET** /ActivePlayerList | N/A
[**playerAPlayerCodeGet**](PlayerApi.md#playerAPlayerCodeGet) | **GET** /Player/{APlayerCode} | N/A


<a name="activePlayerListGet"></a>
# **activePlayerListGet**
> String activePlayerListGet(refdate, windowsize)

N/A

### Example
```java
// Import classes:
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.Configuration;
//import org.openapitools.client.auth.*;
//import com.aardwark.players.api.PlayerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: ts_auth
HttpBasicAuth ts_auth = (HttpBasicAuth) defaultClient.getAuthentication("ts_auth");
ts_auth.setUsername("YOUR USERNAME");
ts_auth.setPassword("YOUR PASSWORD");

PlayerApi apiInstance = new PlayerApi();
String refdate = "refdate_example"; // String | N/A
String windowsize = "windowsize_example"; // String | N/A
try {
    String result = apiInstance.activePlayerListGet(refdate, windowsize);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlayerApi#activePlayerListGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **refdate** | **String**| N/A | [optional]
 **windowsize** | **String**| N/A | [optional]

### Return type

**String**

### Authorization

[ts_auth](../README.md#ts_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/xml

<a name="playerAPlayerCodeGet"></a>
# **playerAPlayerCodeGet**
> String playerAPlayerCodeGet(aplayerCode)

N/A

### Example
```java
// Import classes:
//import org.openapitools.client.ApiClient;
//import org.openapitools.client.ApiException;
//import org.openapitools.client.Configuration;
//import org.openapitools.client.auth.*;
//import com.aardwark.players.api.PlayerApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: ts_auth
HttpBasicAuth ts_auth = (HttpBasicAuth) defaultClient.getAuthentication("ts_auth");
ts_auth.setUsername("YOUR USERNAME");
ts_auth.setPassword("YOUR PASSWORD");

PlayerApi apiInstance = new PlayerApi();
String aplayerCode = "aplayerCode_example"; // String | N/A
try {
    String result = apiInstance.playerAPlayerCodeGet(aplayerCode);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PlayerApi#playerAPlayerCodeGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **aplayerCode** | **String**| N/A |

### Return type

**String**

### Authorization

[ts_auth](../README.md#ts_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/xml

