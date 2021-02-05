// IAppZygoteDetectionResult.aidl
package xyz.cirno.brokensandbox;

interface IAppZygoteDetectionResult {
    boolean isSetUidSucceeded();
    String getStat();
    String[] getEntries();
}
