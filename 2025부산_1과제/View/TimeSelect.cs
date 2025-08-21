using _2025부산_1과제.Controls;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Text;
using System.Windows.Forms;

namespace _2025부산_1과제.View
{
    public partial class TimeSelect : _2025부산_1과제.Template.BF
    {
        List<DayLabel> dayLabels = new List<DayLabel>();
        List<Label> timeLabels = new List<Label>();
        DateTime selDate;
        TimeSpan selTime;

        public TimeSelect()
        {
            InitializeComponent();
        }

        private void TimeSelect_Load(object sender, EventArgs e)
        {
            using (var db = new Model.ITTRAINEntities())
            {
                db.Station.Attach(Hp.start);
                db.Station.Attach(Hp.end);
                label2.Text = $"{Hp.start.Information.stationname} → {Hp.end.Information.stationname}";
                label3.Text = DateTime.Now.ToString("yyyy년 MM월 dd일(ddd) HH:mm");

                var list = getDateList();
                foreach (var item in list)
                {
                    DayLabel dayLabel = new DayLabel() { date = item };
                    dayLabel.label1.Text = item.ToString("ddd");
                    dayLabel.label2.Text = item.Day.ToString();

                    dayLabel.label1.ForeColor = item.DayOfWeek == DayOfWeek.Sunday ? Color.Red : item.DayOfWeek == DayOfWeek.Saturday ? Color.Blue : Color.Black;
                    dayLabel.label2.ForeColor = item.DayOfWeek == DayOfWeek.Sunday ? Color.Red : item.DayOfWeek == DayOfWeek.Saturday ? Color.Blue : Color.Black;

                    int w = panel1.Width / 10;
                    dayLabel.Size = new Size(w, panel1.Height);
                    dayLabel.Location = new Point(w * dayLabels.Count, 0);
                    foreach (Control item1 in dayLabel.tableLayoutPanel1.Controls)
                    {
                        item1.MouseDown += DayLabel_MouseDown;
                        item1.MouseUp += DayLabel_MouseUp;
                        item1.MouseMove += DayLabel_MouseMove;
                        item1.Tag = item;
                    }

                    panel1.Controls.Add(dayLabel);
                    dayLabels.Add(dayLabel);
                }
                selectDate(DateTime.Now.Date);
            }
        }

        private void selectDate(DateTime dateTime)
        {
            foreach (var item in dayLabels)
            {
                if (item.date == dateTime)
                {
                    item.label1.BackColor = Color.Yellow;
                    item.label2.BackColor = Color.Yellow;
                }
                else
                {
                    item.label1.BackColor = Color.White;
                    item.label2.BackColor = Color.White;
                }
            }
            selDate = dateTime;
            addTimeLabel();
        }

        private void addTimeLabel()
        {
            panel2.Controls.Clear();
            timeLabels.Clear();
            TimeSpan start = Hp.division.d_sTime.Value;
            if (start < DateTime.Now.TimeOfDay && DateTime.Now.Date == selDate)
            {
                while (start < DateTime.Now.TimeOfDay)
                {
                    start = start.Add(TimeSpan.FromHours(1));
                }
            }
            TimeSpan end = Hp.division.d_eTime.Value;
            int w = panel2.Width / 10;
            while (start <= end)
            {
                Label lbl = new Label
                {
                    Text = start.Hours + "시",
                    TextAlign = ContentAlignment.MiddleCenter,
                    Size = new Size(w, panel2.Height),
                    BackColor = Color.White,
                    Tag = start,
                    Location = new Point(w * timeLabels.Count, 0)
                };
                lbl.Click += Lbl_Click;
                lbl.MouseDown += Lbl_MouseDown;
                lbl.MouseUp += Lbl_MouseUp;
                lbl.MouseMove += Lbl_MouseMove;

                timeLabels.Add(lbl);
                panel2.Controls.Add(lbl);
                start = start.Add(TimeSpan.FromHours(1));
            }
            if (timeLabels.Count > 0)
                SelectTime(timeLabels[0]);
        }

        private void Lbl_MouseMove(object sender, MouseEventArgs e)
        {
            if (drag2)
            {
                int dx = e.X - x2;
                if (timeLabels.Count > 0 && timeLabels[0].Left + dx > 0 || timeLabels.Last().Right < panel2.Width + dx) return;
                foreach (var item in timeLabels)
                {
                    item.Left += dx;
                }
            }
        }

        private void Lbl_MouseUp(object sender, MouseEventArgs e)
        {
            drag2 = false;
        }
        bool drag2;
        int x2;
        private void Lbl_MouseDown(object sender, MouseEventArgs e)
        {
            drag2 = true;
            x2 = e.X;
        }

        private void SelectTime(Label label)
        {
            foreach (var item in timeLabels)
            {
                item.BackColor = Color.White;
            }
            label.BackColor = Color.Yellow;
            selTime = (TimeSpan)label.Tag;
        }

        private void Lbl_Click(object sender, EventArgs e)
        {
            SelectTime(sender as Label);
        }

        private void DayLabel_MouseUp(object sender, MouseEventArgs e)
        {
            drag = false;
            Console.WriteLine("up");
        }

        bool drag = false;
        int x;

        private void DayLabel_MouseDown(object sender, MouseEventArgs e)
        {
            Console.WriteLine("down");
            drag = true;
            x = e.X;
        }

        private void DayLabel_MouseMove(object sender, MouseEventArgs e)
        {
            if (drag)
            {
                Console.WriteLine("move");
                int dx = e.X - x;
                if (dayLabels.Count > 0 && dayLabels[0].Left + dx > 0 || dayLabels.Last().Right + dx < panel2.Width) return;
                foreach (var item in dayLabels)
                {
                    item.Left += dx;
                }
                Console.WriteLine(2);
            }
        }

        private List<DateTime> getDateList()
        {
            DateTime start = DateTime.Now.Date;
            DateTime end = start.AddMonths(1);
            List<DateTime> list = new List<DateTime>();
            while (start != end)
            {
                list.Add(start);
                start = start.AddDays(1);
            }

            return list;

        }

        private void button2_Click(object sender, EventArgs e)
        {
            Hp.selDate = selDate;
            Hp.selTime = selTime;
            showPage(new View.ScheduleList());
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var c = new Calendar { dayList = getDateList()};
            c.Left = Left+c.Width/2;
            c.Top = Top + c.Height/2;
            c.FormClosed += (s, e2) =>
            {
                selDate = c.selDate;
                selectDate(selDate);
            };
            c.ShowDialog();

        }
    }
}
