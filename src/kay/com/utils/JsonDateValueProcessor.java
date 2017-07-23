package kay.com.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kay on 2017/7/22.
 * 创建一个时间转换器，主要解决json转换Date对象时的格式问题
 * 1.Date格式
 *
 *
 *
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
    private String format ="yyyy-MM-dd";

    public JsonDateValueProcessor() {
        super();
    }

    public JsonDateValueProcessor(String format) {
        super();
        this.format = format;
    }

    @Override
    public Object processArrayValue(Object paramObject,
                                    JsonConfig paramJsonConfig) {
        return process(paramObject);
    }

    @Override
    public Object processObjectValue(String paramString, Object paramObject,
                                     JsonConfig paramJsonConfig) {
        return process(paramObject);
    }


    private Object process(Object value){
        if(value instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
            return sdf.format(value);
        }
        //添加一个对BigDecimal的处理，转换的时候保留2为小数
        if(value instanceof BigDecimal){
            BigDecimal val=(BigDecimal)value;
            //转换为String 保留2位小数
            return val.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return value == null ? "" : value.toString();
    }
}
