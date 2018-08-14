import java.io.File;
import java.io.IOException;

import com.maxmind.geoip.*;

public class Geolocator{
    public static void main(String[] args) {
        File file = new File("/Users/juancasian/Documents/JavaCourse/CodeClinicJava/Geolocator/res/GeoLiteCity.dat");
        ServerLocation serverLocation = getLocation("64.62.224.30", file);
        printLocation(serverLocation);
    }

    public static void printLocation(ServerLocation serverLocation) {
        System.out.println("You are near the city of " + serverLocation.getCity() + ", "
        + serverLocation.getCountryCode() + ".");
        System.out.println("Your approximate location on the maps is:");
        System.out.println("https://www.google.com/maps/?q=" + serverLocation.getLatitude() + ","
        + serverLocation.getLongitude());
    }

    public static ServerLocation getLocation(String ipAddress, File file) {

        ServerLocation serverLocation = null;

        try {

            serverLocation = new ServerLocation();

            LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
            Location locationServices = lookup.getLocation(ipAddress);

            serverLocation.setCountryCode(locationServices.countryCode);
            serverLocation.setCountryName(locationServices.countryName);
            serverLocation.setRegion(locationServices.region);
            serverLocation.setRegionName(regionName.regionNameByCode(
                    locationServices.countryCode, locationServices.region));
            serverLocation.setCity(locationServices.city);
            serverLocation.setPostalCode(locationServices.postalCode);
            serverLocation.setLatitude(String.valueOf(locationServices.latitude));
            serverLocation.setLongitude(String.valueOf(locationServices.longitude));

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return serverLocation;

    }
}
