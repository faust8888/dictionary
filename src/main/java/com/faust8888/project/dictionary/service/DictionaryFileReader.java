package com.faust8888.project.dictionary.service;

import com.faust8888.project.dictionary.exception.DictionaryInvalidFormatRuntimeException;
import com.faust8888.project.dictionary.items.Word;
import com.faust8888.project.dictionary.items.WordBuilder;
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


    public List<Word> readDictionary(final String fileName) throws Exception {
        Iterator<Sheet> sheetIterator = createSheetIterator(fileName);
        return read(sheetIterator);
    }

    private List<Word> read(final Iterator<Sheet> sheetIterator) {
        List<Word> wordList = new ArrayList<>();
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            Iterator<Row> rowIterator = sheet.rowIterator();
            List<Word> words = readRows(rowIterator, sheet.getPhysicalNumberOfRows());
            wordList.addAll(words);
        }
        return wordList;
    }

    private List<Word> readRows(Iterator<Row> rowIterator, int countRows) {
        List<Word> wordList = new ArrayList<>(countRows);
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Word word = createWord(row);
            wordList.add(word);
        }
        return wordList;
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
