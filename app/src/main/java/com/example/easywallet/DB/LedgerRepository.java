package com.example.easywallet.DB;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class LedgerRepository {
    private Context context;

    public LedgerRepository(Context context) {
        this.context = context;
    }

    public  void getLedger(callBack callBack){
        GetTask getTask = new GetTask(context,callBack);
        getTask.execute();
    }
    public  void InsertLedger(LedgerItem item,insertCallBack insertCallBack){
        InsertTask insertTask = new InsertTask(context,insertCallBack);
        insertTask.execute(item);
    }
    public  void getSum(getSumCallBack callBack){
        sumTask getTask = new sumTask(context, callBack) {
        };
        getTask.execute();
    }
    private static class GetTask extends  AsyncTask<Void,Void, List<LedgerItem>>{
        private Context mContext;
        private callBack callBack;
        public GetTask(Context mContext , callBack callBack) {
            this.mContext = mContext;
            this.callBack = callBack;

        }


        @Override
        protected List<LedgerItem> doInBackground(Void... voids) {
            AppDB db = AppDB.getInstance(mContext);
            List<LedgerItem> l1 = db.ledgerDAO().getAll();
            return l1;
        }

        @Override
        protected void onPostExecute(List<LedgerItem> ledgerItems) {
            super.onPostExecute(ledgerItems);
            callBack.getLedgerCallBack(ledgerItems);

        }
    }

    public interface callBack{
        void getLedgerCallBack(List<LedgerItem> LedgerItemList);
    }

    public interface insertCallBack{
        void onInsertCallBack();
    }
    public interface getSumCallBack{
        void onSumCallBack(int sum);
    }

    private static class sumTask extends AsyncTask<Void , Void , Integer>{
        private Context mContext;
        private getSumCallBack callBack;
        public sumTask(Context mContext , getSumCallBack callBack) {
            this.mContext = mContext;
            this.callBack = callBack;

        }


        @Override
        protected Integer doInBackground(Void... voids) {
            AppDB db = AppDB.getInstance(mContext);
            int l1 = db.ledgerDAO().getSum();
            return l1;
        }

        @Override
        protected void onPostExecute(Integer ledgerItems) {
            super.onPostExecute(ledgerItems);
            callBack.onSumCallBack(ledgerItems);

        }

    }

    private static class InsertTask extends AsyncTask<LedgerItem,Void, Void> {
        private Context mContext;
        private insertCallBack insertCallBack;
        public InsertTask(Context mContext , insertCallBack insertCallBack) {
            this.mContext = mContext;
            this.insertCallBack = insertCallBack;
        }

        @Override
        protected Void doInBackground(LedgerItem... ledgerItems) {
            AppDB db = AppDB.getInstance(mContext);
            db.ledgerDAO().insert(ledgerItems[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            insertCallBack.onInsertCallBack();
        }
    }
}
