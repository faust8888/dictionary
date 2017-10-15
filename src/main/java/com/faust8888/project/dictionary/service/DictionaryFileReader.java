package com.faust8888.project.dictionary.service;

import com.faust8888.project.dictionary.exception.DictionaryInvalidFormatRuntimeException;
import com.faust8888.project.dictionary.items.*;
import com.faust8888.project.dictionary.items.Dictionary;
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


    public Dictionary readDictionary(final String fileName) throws Exception {
        Iterator<Sheet> sheetIterator = createSheetIterator(fileName);
        return read(sheetIterator);
    }

    private Dictionary read(final Iterator<Sheet> sheetIterator) {
        Map<String, Word> wordMap = new HashMap<>();
        while (sheetIterator.hasNext()) {
            Map<String, Word> oneSheetWordMap = createMapWord(sheetIterator.next());
            wordMap.putAll(oneSheetWordMap);
        }
        return new Dictionary(wordMap);
    }

    private Map<String, Word> createMapWord(final Sheet sheet) {
        Iterator<Row> rowIterator = sheet.rowIterator();
        return readRows(rowIterator, sheet.getPhysicalNumberOfRows());
    }

    private Map<String, Word> readRows(Iterator<Row> rowIterator, int countRows) {
        Map<String,Word> mapWord = new HashMap<>(countRows);
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Word word = createWord(row);
            if(mapWord.containsKey(word.getWord())) {
                continue;
            }
            mapWord.put(word.getWord(), word);
        }
        return mapWord;
    }

    private Iterator<Sheet> createSheetIterator(final String fileName) throws Exception{
        FileInputStream excelFile = new FileInputStream(new File(fileName));
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);

        return workbook.sheetIterator();
    }

    private Word createWord(final Row row) {
        WordBuilder wordBuilder = new WordBuilder();

        int count = 0;
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String value = cell.getStringCellValue();

            switch (count) {
                case WORD_COLUMN:
                    wordBuilder.setWord(value);
                    break;
                case MEANING_COLUMN:
                    wordBuilder.setMeaning(value);
                    break;
                case TRANSLATE_COLUMN:
                    wordBuilder.setTranslate(value);
                    break;
                case CONTEXT_COLUMN:
                    wordBuilder.setContext(value);
                    break;
                default:
                    throw new DictionaryInvalidFormatRuntimeException("Invalid Dictionary Format.");
            }
            count++;
            if(wordBuilder.isWordReady()) {
                break;
            }
        }
        return wordBuilder.buildWord();
    }
}
