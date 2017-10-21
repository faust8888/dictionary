package com.faust8888.project.dictionary.service;

import com.faust8888.project.dictionary.exception.DictionaryInvalidFormatRuntimeException;
import com.faust8888.project.dictionary.viewItems.*;
import com.faust8888.project.dictionary.viewItems.DictionaryView;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;


@Service
@Scope(scopeName = "singleton")
@Order(1)
public class DictionaryFileReader {

    private static final int WORD_COLUMN = 0;
    private static final int MEANING_COLUMN = 1;
    private static final int TRANSLATE_COLUMN = 2;
    private static final int CONTEXT_COLUMN = 3;


    public DictionaryView readDictionary(final String fileName) throws Exception {
        Iterator<Sheet> sheetIterator = createSheetIterator(fileName);
        return read(sheetIterator);
    }

    private DictionaryView read(final Iterator<Sheet> sheetIterator) {
        Map<String, WordView> wordMap = new HashMap<>();
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();

            //TODO: need to change in the future
            if(sheet.getSheetName().equals("Phrases") || sheet.getSheetName().equals("Topics")) {
                continue;
            }

            Map<String, WordView> oneSheetWordMap = createMapWord(sheet);
            wordMap.putAll(oneSheetWordMap);
        }
        return new DictionaryView(wordMap);
    }

    private Map<String, WordView> createMapWord(final Sheet sheet) {
        Iterator<Row> rowIterator = sheet.rowIterator();
        return readRows(rowIterator, sheet.getPhysicalNumberOfRows());
    }

    private Map<String, WordView> readRows(final Iterator<Row> rowIterator,final int countRows) {
        Map<String,WordView> mapWord = new HashMap<>(countRows);
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            WordView wordView = createWord(row);
            if(mapWord.containsKey(wordView.getWord())) {
                continue;
            }
            mapWord.put(wordView.getWord(), wordView);
        }
        return mapWord;
    }

    private Iterator<Sheet> createSheetIterator(final String fileName) throws Exception{
        FileInputStream excelFile = new FileInputStream(new File(fileName));
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);

        return workbook.sheetIterator();
    }

    private WordView createWord(final Row row) {
        WordViewBuilder wordViewBuilder = new WordViewBuilder();

        int count = 0;
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String value = cell.getStringCellValue();

            switch (count) {
                case WORD_COLUMN:
                    wordViewBuilder.setWord(value);
                    break;
                case MEANING_COLUMN:
                    wordViewBuilder.setMeaning(value);
                    break;
                case TRANSLATE_COLUMN:
                    wordViewBuilder.setTranslate(value);
                    break;
                case CONTEXT_COLUMN:
                    wordViewBuilder.setContext(value);
                    break;
                default:
                    throw new DictionaryInvalidFormatRuntimeException("Invalid DictionaryView Format.");
            }
            count++;
            if(wordViewBuilder.isWordReady()) {
                break;
            }
        }
        return wordViewBuilder.buildWord();
    }
}
