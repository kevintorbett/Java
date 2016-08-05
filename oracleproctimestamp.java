public static void procedureStop(ParUnitOfWork uow) {
       // timestamp format 'YYYY-MM-DD HH24:MI:SS' use timestamp not date
         try {
            def sql = new Sql(uow.dataSource)
            sql.call '{call PKG_XXXX.p_xxxx(?,?,?)}',
                    [uow.Id,Sql.TIMESTAMP],
                    { o_workbook_key,o_report_date, o_return_code ->
                        uow.workbook_key = o_workbook_key
                        uow.report_date    = o_report_date
                        uow.returnStatus = o_return_code
                    }
        String date_yyyy=uow.report_date..substring(0,3)
        String date_mm=uow.report_date..substring(5,2)
        String date_dd=uow.report_date..substring(8,2)
        String date_hh=uow.report_date..substring(11,2)
        String date_mi=uow.report_date..substring(14,2)
        String date_ss=uow.report_date..substring(17,2)
       
    
        } catch (Exception e) {
            ParQueueSQLDAO.processOracleException( e, uow)
        }
        catch (IOException ioe) {
            ParQueueSQLDAO.processOracleException( ioe, uow)
        }
        catch (FileNotFoundException fe) {
            ParQueueSQLDAO.processOracleException( fe, uow)
        }
        catch (ZipException ze) {
            ParQueueSQLDAO.processOracleException( ze, uow)
        }
    }