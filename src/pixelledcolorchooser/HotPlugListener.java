package pixelledcolorchooser;

import javax.usb.UsbDevice;
import javax.usb.event.UsbServicesEvent;
import javax.usb.event.UsbServicesListener;

public class HotPlugListener implements UsbServicesListener {

    @Override
    public void usbDeviceAttached(UsbServicesEvent use) {
        UsbDevice device = use.getUsbDevice();
        System.out.println(getDeviceInfo(device) + " was added to the bus.");
    }

    @Override
    public void usbDeviceDetached(UsbServicesEvent use) {
        UsbDevice device = use.getUsbDevice();
        System.out.println(getDeviceInfo(device) + " was removed from the bus.");
    }

    private static String getDeviceInfo(UsbDevice device) {
        try {
            String product = device.getProductString();
            String serial = device.getSerialNumberString();
            if (product == null) {
                return "Unknown USB device";
            }
            if (serial != null) {
                return product + " " + serial;
            } else {
                return product;
            }
        } catch (Exception ex) {
        }
        return "Unknown USB device";
    }

}
