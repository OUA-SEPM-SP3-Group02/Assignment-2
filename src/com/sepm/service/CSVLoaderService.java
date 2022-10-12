package com.sepm.service;

import com.sepm.model.StaffMember;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVLoaderService {

    public static List<StaffMember> loadStaffMembers(String csv) throws IOException {
        List<StaffMember> staffMembers = new ArrayList<>();
        Path pathToFile = Paths.get(csv);


        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            String line = br.readLine();

            while (line!=null) {
                String [] attributes = line.split(",");

                StaffMember staffMember = createStaffMember(attributes);

                staffMembers.add(staffMember);

                line = br.readLine();
            }
        }
        return staffMembers;
    }

    private static StaffMember createStaffMember(String[] metadata) {
        String id = metadata[0];
        String name = metadata[1];
        String email = metadata[2];
        String phone = metadata[3];
        String password = metadata[4];


        return new StaffMember(id, name, email, phone, password);
    }

}

