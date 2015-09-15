package co.iyubinest.payuchallenge.presentation.client;

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
import co.iyubinest.payuchallenge.domain.client.Client;

public class ClientListAdapter extends ArrayAdapter<Client> {

	interface ClientClickListener {
		void onClick(Client client);
	}

	private final ClientClickListener clientClickListener;

	private final LayoutInflater inflater;

	ViewHolder viewHolder;

	public ClientListAdapter(Context context, List<Client> clientList,
			ClientClickListener clientClickListener) {
		super(context, R.layout.client_list_item_row,
				R.id.client_list_item_row_title, clientList);
		this.inflater = ((Activity) context).getLayoutInflater();
		this.clientClickListener = clientClickListener;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = resolveHolder(convertView, parent);
		Client client = getItem(position);
		viewHolder.title.setText(client.getName());
		setListener(convertView, client);
		return convertView;
	}

	private void setListener(View view, final Client client) {
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (clientClickListener != null) {
					clientClickListener.onClick(client);
				}
			}
		});
	}

	private View resolveHolder(View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.client_list_item_row,
					parent, false);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.client_list_item_row_title);

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
