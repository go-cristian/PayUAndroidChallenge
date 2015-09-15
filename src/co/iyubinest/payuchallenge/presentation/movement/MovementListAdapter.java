package co.iyubinest.payuchallenge.presentation.movement;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import co.iyubinest.payuchallenge.R;
import co.iyubinest.payuchallenge.domain.movement.Movement;

public class MovementListAdapter extends ArrayAdapter<Movement> {

	ViewHolder viewHolder;

	public MovementListAdapter(Context context, List<Movement> clientList) {
		super(context, R.layout.movement_list_item_row,
				R.id.movement_list_item_row_title, clientList);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = resolveHolder(convertView, parent);
		Movement movement = getItem(position);
		viewHolder.title.setText(String.valueOf(movement.getValue()));
		return convertView;
	}

	private View resolveHolder(View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) getContext())
					.getLayoutInflater();
			convertView = inflater.inflate(R.layout.movement_list_item_row,
					parent, false);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.movement_list_item_row_title);

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
