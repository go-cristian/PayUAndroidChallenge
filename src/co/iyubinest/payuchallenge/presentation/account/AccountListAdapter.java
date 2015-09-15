package co.iyubinest.payuchallenge.presentation.account;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import co.iyubinest.payuchallenge.R;
import co.iyubinest.payuchallenge.domain.account.Account;

public class AccountListAdapter extends ArrayAdapter<Account> {

	interface AccountClickListener {
		void onClick(Account account);
	}

	private final AccountClickListener accountClickListener;

	ViewHolder viewHolder;

	public AccountListAdapter(Context context, List<Account> clientList,
			AccountClickListener accountClickListener) {
		super(context, R.layout.account_list_item_row,
				R.id.account_list_item_row_title, clientList);
		this.accountClickListener = accountClickListener;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = resolveHolder(convertView, parent);
		Account account = getItem(position);
		viewHolder.title.setText(String.valueOf(account.getNumber()));
		setListener(convertView, account);
		return convertView;
	}

	private void setListener(View view, final Account account) {
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (accountClickListener != null) {
					accountClickListener.onClick(account);
				}
			}
		});
	}

	private View resolveHolder(View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) getContext())
					.getLayoutInflater();
			convertView = inflater.inflate(R.layout.account_list_item_row,
					parent, false);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.account_list_item_row_title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	static class ViewHolder {
		TextView title;
	}
}
