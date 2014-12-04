package kr.ac.uos.cs.thechoice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by TYMBP on 2014. 12. 3..
 */
public class ArticleListAdapter extends BaseAdapter {
    // 문자열을 보관 할 ArrayList
    private ArrayList<HashMap<String, Object>> m_List;
    private AsyncImageLoader imageLoader = new AsyncImageLoader();    // 생성자
    private ViewHolder holder;
    public ArticleListAdapter() {
        m_List = new ArrayList<HashMap<String, Object>>();
    }

    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return m_List.size();
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return m_List.get(position);
    }

    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // item list 를 받음
    public ArrayList<HashMap<String, Object>> getList(){
        return m_List;
    }

    // 출력 될 아이템 관리
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if ( convertView == null ) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.article_listitem, parent, false);

            // TextView에 현재 position의 문자열 추가
//            TextView text = (TextView) convertView.findViewById(R.id.listitem_article_title);
            holder = new ViewHolder();

            TextView title = (TextView) convertView.findViewById(R.id.listitem_article_title);
            TextView gender = (TextView) convertView.findViewById(R.id.listitem_article_gender);
            TextView nearstation = (TextView) convertView.findViewById(R.id.listitem_article_nearstation);
            TextView desc = (TextView) convertView.findViewById(R.id.listitem_article_description);


            ImageView img = (ImageView) convertView.findViewById(R.id.listitem_article_image);
            holder.img = img;
            ImageView viewBtn = (ImageView) convertView.findViewById(R.id.listitem_article_btnview);
            String imageURL = "http://dlacodbs90.cafe24.com" + m_List.get(position).get("photo_path");
            Log.e("FFFF",imageURL);
            imageLoader.setImageDrawableAsync(holder.img, imageURL, context);
            title.setText((String)m_List.get(position).get("title"));
            gender.setText((String)m_List.get(position).get("gender"));
            nearstation.setText((String)m_List.get(position).get("near_station"));
            desc.setText((String)m_List.get(position).get("desc"));

//            img.setImageBitmap((Bitmap)m_List.get(position).get("img"));

            viewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,(String) m_List.get(pos).get("idx"), Toast.LENGTH_LONG);
                }
            });


//            text.setText(m_List.get(position));

            // 버튼을 터치 했을 때 이벤트 발생

            // 리스트 아이템을 터치 했을 때 이벤트 발생

            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, "리스트 클릭 : "+m_List.get(pos), Toast.LENGTH_SHORT).show();
                }
            });

            // 리스트 아이템을 길게 터치 했을 떄 이벤트 발생
            convertView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, "리스트 롱 클릭 : "+m_List.get(pos), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

        return convertView;
    }
    // 외부에서 아이템 추가 요청 시 사용
    public void add(HashMap<String, Object> data) {
        m_List.add(data);
    }

    // 외부에서 아이템 삭제 요청 시 사용
    public void remove(int _position) {
        m_List.remove(_position);
    }
}
class ViewHolder
{
    public ImageView img;
}
