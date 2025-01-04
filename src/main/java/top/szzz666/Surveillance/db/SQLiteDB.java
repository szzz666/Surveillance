package top.szzz666.Surveillance.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import top.szzz666.Surveillance.entity.Params;
import top.szzz666.Surveillance.entity.RetrunQuery;
import top.szzz666.Surveillance.entity.SeData;

import java.sql.SQLException;
import java.util.List;

import static top.szzz666.Surveillance.SurveillanceMain.ConfigPath;
import static top.szzz666.Surveillance.config.SeConfig.maxPageSize;
import static top.szzz666.Surveillance.tools.pluginUtil.isEmptyString;
import static top.szzz666.Surveillance.tools.pluginUtil.nkConsole;

public class SQLiteDB {
    private static final String DATABASE_URL = "jdbc:sqlite:" + ConfigPath + "/Surveillance.db";
    public ConnectionSource conn;
    public Dao<SeData, Integer> dataDao;

    public SQLiteDB() {
        try {
            conn = new JdbcConnectionSource(DATABASE_URL);
            nkConsole("&a数据库连接成功");
            TableUtils.createTableIfNotExists(conn, SeData.class);
            dataDao = DaoManager.createDao(conn, SeData.class);
        } catch (SQLException e) {
            nkConsole(e.getMessage(), 2);
        }
    }

    public void dbAdd(SeData data) {
        try {
            if (dataDao.create(data) < 0) {
                nkConsole("&c数据库写入失败", 2);
            }
        } catch (SQLException e) {
            nkConsole(e.getMessage(), 2);
        }
    }

    public String dbQuery(Params params) {
        // 计算分页参数
        long limit = params.getPageSize() > maxPageSize ? maxPageSize : params.getPageSize();
        long offset = (params.getCurrent() - 1) * limit;
        List<SeData> seDataList;
        try {
//            if (params.getId() > 0) {
//                SeData seData = dataDao.queryForId(params.getId());
//                seDataList.add(seData);
//                return new RetrunQuery(1L, seDataList).toJson();
//            }
            QueryBuilder<SeData, Integer> qb = dataDao.queryBuilder();
            if (haveWhere(params)) {
                Where<SeData, Integer> where = qb.where();
                // 标志变量，用于跟踪是否已经添加了第一个条件
                boolean isFirstCondition = true;
                // 动态添加查询条件
                if (params.getId() > 0) {
                    where.eq("id", params.getId());
                    isFirstCondition = false;
                }
                if (!isEmptyString(params.getTime())) {
                    if (!isFirstCondition) {
                        where.and();
                    }
                    where.like("time", "%" + params.getTime() + "%");
                    isFirstCondition = false;
                }
                if (!isEmptyString(params.getPlayer_name())) {
                    if (!isFirstCondition) {
                        where.and();
                    }
                    where.like("player_name", "%" + params.getPlayer_name() + "%");
                    isFirstCondition = false;
                }
                if (!isEmptyString(params.getPlayer_uuid())) {
                    if (!isFirstCondition) {
                        where.and();
                    }
                    where.like("player_uuid", "%" + params.getPlayer_uuid() + "%");
                    isFirstCondition = false;
                }
                if (!isEmptyString(params.getPlayer_pos())) {
                    if (!isFirstCondition) {
                        where.and();
                    }
                    where.like("player_pos", "%" + params.getPlayer_pos() + "%");
                    isFirstCondition = false;
                }
                if (!isEmptyString(params.getEvent_pos())) {
                    if (!isFirstCondition) {
                        where.and();
                    }
                    where.like("event_pos", "%" + params.getEvent_pos() + "%");
                    isFirstCondition = false;
                }
                if (!isEmptyString(params.getEvent_block())) {
                    if (!isFirstCondition) {
                        where.and();
                    }
                    where.like("event_block", "%" + params.getEvent_block() + "%");
                    isFirstCondition = false;
                }
                if (!isEmptyString(params.getEvent_item())) {
                    if (!isFirstCondition) {
                        where.and();
                    }
                    where.like("event_item", "%" + params.getEvent_item() + "%");
                    isFirstCondition = false;
                }
                if (!isEmptyString(params.getEvent_other())) {
                    if (!isFirstCondition) {
                        where.and();
                    }
                    where.like("event_other", "%" + params.getEvent_other() + "%");
                    isFirstCondition = false;
                }
                if (!isEmptyString(params.getEvent_type())) {
                    if (!isFirstCondition) {
                        where.and();
                    }
                    where.like("event_type", "%" + params.getEvent_type() + "%");
                }
            }
            Long total = qb.countOf();
            seDataList = qb.limit(limit).offset(offset).query();
            return new RetrunQuery(total, seDataList).toJson();
        } catch (SQLException e) {
            nkConsole(e.getMessage(), 2);
            return e.getMessage();
        }
    }

    private boolean haveWhere(Params params) {
        if (params.getId() > 0) {
            return true;
        }
        if (!isEmptyString(params.getTime())) {
            return true;
        }
        if (!isEmptyString(params.getPlayer_name())) {
            return true;
        }
        if (!isEmptyString(params.getPlayer_uuid())) {
            return true;
        }
        if (!isEmptyString(params.getPlayer_pos())) {
            return true;
        }
        if (!isEmptyString(params.getEvent_pos())) {
            return true;
        }
        if (!isEmptyString(params.getEvent_block())) {
            return true;
        }
        if (!isEmptyString(params.getEvent_item())) {
            return true;
        }
        if (!isEmptyString(params.getEvent_other())) {
            return true;
        }
        return !isEmptyString(params.getEvent_type());
    }

}

