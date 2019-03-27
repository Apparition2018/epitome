package utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectXML {

    public static void objectXmlEncoder(Object obj, String fileName)
            throws FileNotFoundException, IOException, Exception {
        String path = fileName.substring(0, fileName.lastIndexOf('/'));
        if (path.indexOf('/') != -1) {
            File pFile = new File(path);
            if (!pFile.exists()) {
                pFile.mkdirs();
            }
        }
        File fo = new File(fileName);
        FileOutputStream fos = new FileOutputStream(fo);
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.writeObject(obj);
        encoder.flush();
        encoder.close();
        fos.close();
    }

    public static String objectXmlEncoder(Object obj) throws FileNotFoundException, IOException, Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(baos);
        encoder.writeObject(obj);
        encoder.flush();
        encoder.close();
        String rtnVal = new String(baos.toByteArray(), "UTF-8");
        baos.close();
        return rtnVal;
    }

    public static List objectXmlDecoder(String objSource)
            throws FileNotFoundException, IOException, Exception {
        File fin = new File(objSource);
        return objectXmlDecoder(new FileInputStream(fin));
    }

    public static List objectXMLDecoderByString(String ins) throws Exception {
        return objectXmlDecoder(new ByteArrayInputStream(ins.getBytes("UTF-8")));
    }

    public static List objectXmlDecoder(InputStream ins) throws IOException, Exception {
        List objList = new ArrayList();
        XMLDecoder decoder = new XMLDecoder(ins);
        Object obj = null;
        try {
            while ((obj = decoder.readObject()) != null)
                objList.add(obj);
        } catch (Exception localException) {
        }
        ins.close();
        decoder.close();
        return objList;
    }
}