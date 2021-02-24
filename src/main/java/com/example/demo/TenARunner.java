package com.example.demo;

import com.example.demo.db.entity.Competition;
import com.example.demo.db.entity.Driver;
import com.example.demo.db.entity.Team;
import com.example.demo.db.repository.CompetitionRepository;
import com.example.demo.db.repository.DriverRepository;
import com.example.demo.db.repository.TeamRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

@Component
public class TenARunner implements CommandLineRunner {

    private final TeamRepository teamRepository;

    private final DriverRepository driverRepository;

    private final CompetitionRepository competitionRepository;

    public TenARunner(TeamRepository teamRepository, DriverRepository driverRepository, CompetitionRepository competitionRepository) {
        this.teamRepository = teamRepository;
        this.driverRepository = driverRepository;
        this.competitionRepository = competitionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        FileInputStream fis = new FileInputStream(new File("src/main/resources/formula1.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(fis);

        populateDriversAndTeams(wb);
        populateCompetitions(wb);

    }

    private void populateCompetitions(XSSFWorkbook wb) {
        XSSFSheet sheet = wb.getSheetAt(0);
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();

        int i = 0;
        for (Row row : sheet)     //iteration over row using for each loop
        {
            if (i++ == 0) {
                continue;
            }

            Competition competition = new Competition();
            String driverName = "";
            String teamName = "";

            int c = 0;
            for (Cell cell : row) {

                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        int numericCellValue = (int) cell.getNumericCellValue();
                        if (c == 0) {
                            competition.setSeason(numericCellValue);
                        } else if (c == 3) {
                            competition.setPlace(numericCellValue);
                        }

                        break;
                    case Cell.CELL_TYPE_STRING:
                        String stringCellValue = cell.getStringCellValue();

                        if (c == 1) {
                            driverName = stringCellValue;
                        } else if (c == 2) {
                            teamName = stringCellValue;
                        }
                        break;
                }

                Optional<Team> teamOptional = teamRepository.findByName(teamName);
                if (teamOptional.isPresent()) {
                    Team team = teamOptional.get();
                    competition.setTeam(team);
                }

                Optional<Driver> driverOptional = driverRepository.findByName(driverName);
                if (driverOptional.isPresent()) {
                    Driver driver = driverOptional.get();
                    competition.setDriver(driver);
                }

                competitionRepository.save(competition);
                c++;
            }
        }
    }

    private void populateDriversAndTeams(XSSFWorkbook wb) {
        XSSFSheet sheet = wb.getSheetAt(0);
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();

        int i = 0;
        for (Row row : sheet)     //iteration over row using for each loop
        {
            if (i++ == 0) {
                continue;
            }

            Driver driver = new Driver();
            Team team = new Team();

            int c = 0;
            for (Cell cell : row) {

                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        String stringCellValue = cell.getStringCellValue();

                        if (c == 1) {
                            driver.setName(stringCellValue);
                        } else if (c == 2) {
                            team.setName(stringCellValue);
                        }
                        break;
                }

                Optional<Team> teamOptional = teamRepository.findByName(team.getName());
                if (teamOptional.isEmpty()) {
                    teamRepository.save(team);
                }

                Optional<Driver> driverOptional = driverRepository.findByName(driver.getName());
                if (driverOptional.isEmpty()) {
                    driverRepository.save(driver);
                }

                c++;
            }
        }
    }


    private boolean isYear(double numericCellValue) {
        return numericCellValue > 1900;
    }
}
