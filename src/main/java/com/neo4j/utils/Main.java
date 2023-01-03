package com.neo4j.utils;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.apache.commons.cli.*;

import javax.inject.Inject;

@QuarkusMain
public class Main implements QuarkusApplication {

    @Inject
    CypherService cs;


    @Override
    public int run(String... args) throws Exception {

        Options options = new Options();

        {
            Option option = new Option("u", "user", true, "user name");
            option.setRequired(false);
            options.addOption(option);
        }
        {
            Option option = new Option("p", "password", true, "user password");
            option.setRequired(false);
            options.addOption(option);
        }
        {
            Option option = new Option("a", "address", true, "address");
            option.setRequired(false);
            options.addOption(option);
        }
        {
            Option option = new Option("d", "database", true, "database");
            option.setRequired(false);
            options.addOption(option);
        }


        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        try {
            CommandLine cmd = parser.parse(options, args, true);
            if (cmd.hasOption("user")) {
                cs.setUserName(cmd.getOptionValue("user").trim());
            }
            if (cmd.hasOption("password")) {
                cs.setPassword(cmd.getOptionValue("password").trim());
            }
            if (cmd.hasOption("address")) {
                cs.setAddress(cmd.getOptionValue("address").trim());
            }
            if (cmd.hasOption("database")) {
                cs.setDatabase(cmd.getOptionValue("database").trim());
            }
            String query = cmd.getArgList().get(0);
            return cs.query(query);

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
        return 1;
    }
}
