package maximax444.blps.service.usersXML;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XMLAdapter extends XmlAdapter<String, Date> {
    private static final ThreadLocal<DateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    @Override
    public Date unmarshal(String v) throws Exception {
        return dateFormat.get().parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.get().format(v);
    }
}