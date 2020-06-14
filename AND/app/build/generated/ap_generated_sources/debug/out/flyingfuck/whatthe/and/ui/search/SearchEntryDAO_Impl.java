package flyingfuck.whatthe.and.ui.search;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SearchEntryDAO_Impl implements SearchEntryDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SearchEntry> __insertionAdapterOfSearchEntry;

  private final EntityDeletionOrUpdateAdapter<SearchEntry> __deletionAdapterOfSearchEntry;

  private final EntityDeletionOrUpdateAdapter<SearchEntry> __updateAdapterOfSearchEntry;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllEntries;

  public SearchEntryDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSearchEntry = new EntityInsertionAdapter<SearchEntry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `entry_table` (`PKid`,`id`,`members`,`tradeable`,`icon`,`name`,`release_date`,`lowalch`,`highalch`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SearchEntry value) {
        stmt.bindLong(1, value.getPKid());
        stmt.bindLong(2, value.getId());
        final int _tmp;
        _tmp = value.isMembers() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isTradeable() ? 1 : 0;
        stmt.bindLong(4, _tmp_1);
        if (value.getIcon() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getIcon());
        }
        if (value.getName() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getName());
        }
        if (value.getRelease_date() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getRelease_date());
        }
        stmt.bindLong(8, value.getLowalch());
        stmt.bindLong(9, value.getHighalch());
      }
    };
    this.__deletionAdapterOfSearchEntry = new EntityDeletionOrUpdateAdapter<SearchEntry>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `entry_table` WHERE `PKid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SearchEntry value) {
        stmt.bindLong(1, value.getPKid());
      }
    };
    this.__updateAdapterOfSearchEntry = new EntityDeletionOrUpdateAdapter<SearchEntry>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `entry_table` SET `PKid` = ?,`id` = ?,`members` = ?,`tradeable` = ?,`icon` = ?,`name` = ?,`release_date` = ?,`lowalch` = ?,`highalch` = ? WHERE `PKid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SearchEntry value) {
        stmt.bindLong(1, value.getPKid());
        stmt.bindLong(2, value.getId());
        final int _tmp;
        _tmp = value.isMembers() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isTradeable() ? 1 : 0;
        stmt.bindLong(4, _tmp_1);
        if (value.getIcon() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getIcon());
        }
        if (value.getName() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getName());
        }
        if (value.getRelease_date() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getRelease_date());
        }
        stmt.bindLong(8, value.getLowalch());
        stmt.bindLong(9, value.getHighalch());
        stmt.bindLong(10, value.getPKid());
      }
    };
    this.__preparedStmtOfDeleteAllEntries = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM entry_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(final SearchEntry entry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSearchEntry.insert(entry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final SearchEntry entry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfSearchEntry.handle(entry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final SearchEntry entry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfSearchEntry.handle(entry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllEntries() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllEntries.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllEntries.release(_stmt);
    }
  }

  @Override
  public LiveData<List<SearchEntry>> getAllEntries() {
    final String _sql = "SELECT * FROM entry_table ORDER BY release_date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"entry_table"}, false, new Callable<List<SearchEntry>>() {
      @Override
      public List<SearchEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPKid = CursorUtil.getColumnIndexOrThrow(_cursor, "PKid");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMembers = CursorUtil.getColumnIndexOrThrow(_cursor, "members");
          final int _cursorIndexOfTradeable = CursorUtil.getColumnIndexOrThrow(_cursor, "tradeable");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "release_date");
          final int _cursorIndexOfLowalch = CursorUtil.getColumnIndexOrThrow(_cursor, "lowalch");
          final int _cursorIndexOfHighalch = CursorUtil.getColumnIndexOrThrow(_cursor, "highalch");
          final List<SearchEntry> _result = new ArrayList<SearchEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SearchEntry _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final boolean _tmpMembers;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfMembers);
            _tmpMembers = _tmp != 0;
            final boolean _tmpTradeable;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfTradeable);
            _tmpTradeable = _tmp_1 != 0;
            final String _tmpIcon;
            _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpRelease_date;
            _tmpRelease_date = _cursor.getString(_cursorIndexOfReleaseDate);
            final int _tmpLowalch;
            _tmpLowalch = _cursor.getInt(_cursorIndexOfLowalch);
            final int _tmpHighalch;
            _tmpHighalch = _cursor.getInt(_cursorIndexOfHighalch);
            _item = new SearchEntry(_tmpId,_tmpMembers,_tmpTradeable,_tmpIcon,_tmpName,_tmpRelease_date,_tmpLowalch,_tmpHighalch);
            final int _tmpPKid;
            _tmpPKid = _cursor.getInt(_cursorIndexOfPKid);
            _item.setPKid(_tmpPKid);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
