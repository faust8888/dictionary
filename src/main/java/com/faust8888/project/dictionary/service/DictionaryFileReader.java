package com.faust8888.project.dictionary.service;

import com.faust8888.project.dictionary.utils.ExcelUtils;
import com.faust8888.project.dictionary.viewItems.DictionaryView;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;


@Service
@Scope(scopeName = "singleton")
@Order(1)
public class DictionaryFileReader {

    public DictionaryView readDictionary(final String fileName) throws Exception {
        return ExcelUtils.read(fileName);
    }

    public void writeDictionary(final DictionaryView dictionary, final String fileName) throws Exception {
        Workbook workbook = ExcelUtils.createWorkbook(dictionary);
        writeFile(workbook, fileName);
    }

    private void writeFile(final Workbook workbook, final String fileName) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        workbook.write(outputStream);
        workbook.close();
    }
}
