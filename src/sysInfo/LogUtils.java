package sysInfo;

import org.springframework.stereotype.Component;


@Component
public class LogUtils{
	/*
	 * Constatnts
	 */
	/*private static final String DATE_COLUMN = "date";
	private static final String LOGGER_COLUMN = "logger";
	private static final String LEVEL_COLUMN = "level";
	private static final String MSG_COLUMN = "message";
	private static final String EXCEPTION_COLUMN = "exception";
	private static final String APPENDER_NAME = "databaseAppender";
	private static final String TABLE_NAME = "logs";
	private static final Level LOG_LEVEL = Level.WARN;
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
  	private DataSource dataSource;

	//inner class
	class Connect implements ConnectionSource {
		private DataSource dsource;
		
		public Connect(DataSource dsource) {
			this.dsource = dsource;
		}
    
	    @Override
	    public Connection getConnection() throws SQLException {
	        return this.dsource.getConnection();
	    }
	}

	public LogUtils() {
		logger.debug("LogUtils create contructor.");     
	}

	@PostConstruct
	private void init(){
		logger.debug("Initialize LogUtils with dataSource: " + dataSource);    
		final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		final Configuration config = ctx.getConfiguration();
		ColumnConfig[] cc = {
				ColumnConfig.createColumnConfig(config, DATE_COLUMN, null, null, "true", null, null),
				ColumnConfig.createColumnConfig(config, LOGGER_COLUMN, "%c{1}", null, null, null, null),
				ColumnConfig.createColumnConfig(config, LEVEL_COLUMN, "%level", null, null, null, null),
				ColumnConfig.createColumnConfig(config, MSG_COLUMN, "%message", null, null, null, null),
				ColumnConfig.createColumnConfig(config, EXCEPTION_COLUMN, "%ex{full}", null, null, null, null)
		};     
		Appender appender = JdbcAppender.createAppender(APPENDER_NAME, "true", null, new Connect(dataSource), "0", TABLE_NAME , cc);
		appender.start();
		config.addAppender(appender);
		LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
		loggerConfig.addAppender(appender, LOG_LEVEL, null);
		ctx.updateLoggers();        
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/
}
