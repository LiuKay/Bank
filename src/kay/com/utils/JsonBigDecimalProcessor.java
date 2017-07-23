package kay.com.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.math.BigDecimal;

/**
 * Created by kay on 2017/7/23.
 * 后补：
 * 2.添加一个对BigDecimal的处理，转化为json后发现没有小数部分了
 */
public class JsonBigDecimalProcessor implements JsonValueProcessor {

    public JsonBigDecimalProcessor() {
        super();
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
        //添加一个对BigDecimal的处理，转换的时候保留2为小数
        if(value instanceof BigDecimal){
            BigDecimal val=(BigDecimal)value;
            //转换为String 保留2位小数
            return val.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return value == null ? "" : value.toString();
    }
}
