package source.converter;

import org.springframework.core.convert.converter.Converter;

import java.io.File;

public class FileToString implements Converter<File, String> {

    private String stringPattern;

    public FileToString (String stringPattern){
        this.stringPattern = stringPattern;
    }

    @Override
    public String convert(File source) {
        return source.toString();
    }
}
