
# Demo
click on the screenshot to open the demo video

<div align="center">
  <a href="https://www.youtube.com/watch?v=7m0yG9PXvLA">
  <img src="https://github.com/OmarAliSaid/CustomTimePicker/blob/master/ScreenShots/Screenshot_1.jpeg" width="200"></a>
  
   <a href="https://www.youtube.com/watch?v=7m0yG9PXvLA">
  <img src="https://github.com/OmarAliSaid/CustomTimePicker/blob/master/ScreenShots/Screenshot_2.jpeg" width="200"></a>
  
   <a href="https://www.youtube.com/watch?v=7m0yG9PXvLA">
  <img src="https://github.com/OmarAliSaid/CustomTimePicker/blob/master/ScreenShots/Screenshot_3.jpeg" width="200"></a>
  
</div>

# Android Custom Time Picker
this is a demo app for custom time picker in android , you can customize it to show any data relevant to your application 
logic not only as a time picker.

# Usage
``` Java
public class MainActivity extends AppCompatActivity implements TimeSelectedListener {

    @BindView(R.id.tv_selected_time)TextView tv_selected_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }


    @OnClick(R.id.btn_pick_time)
    public void pickTime() {
        TimePickerDialog.Builder timePickerBuilder = new TimePickerDialog.Builder();

        TimePickerDialog timePickerDialog = timePickerBuilder
                .setStartDate("2019/01/20")
                .setEndDate("2019/02/04")
                .setPositiveButtonBackgroundColor("#D81B60")
                .setPositiveButtonTextColor("#000000")
                .setPositiveButtonText("DONE")
                .incrementMinutesBy(3)
                .create();

        timePickerDialog.show(getSupportFragmentManager(), "DIALOG_TIME_PICKER");
    }



    @Override
    public void onTimeSelected(SelectedTimeEvent selectedTime) {
        if(selectedTime!=null)
            tv_selected_time.setText(selectedTime.toString());
    }
}

```
#### Dependencies
* https://github.com/kHRYSTAL/CircleRecyclerView

#### NOTE
I have done some changes on the library code to serve this demo needs .

#### Developed By 
* Omar Ali
* omarali0252@gmail.com
* <a href="https://www.paypal.me/OmarAliSaid">paypal.me/OmarAliSaid</a> <br/>

#### License
```
Copyright 2017 Omar Ali

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

```
