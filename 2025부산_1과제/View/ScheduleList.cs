using _2025부산_1과제.Controls;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net.NetworkInformation;
using System.Text;
using System.Windows.Forms;

namespace _2025부산_1과제.View
{
    public partial class ScheduleList : _2025부산_1과제.Template.BF
    {
        private int price;

        public ScheduleList()
        {
            InitializeComponent();
        }

        private void SchuduleList_Load(object sender, EventArgs e)
        {
            label2.Text = $"{Hp.start.Information.stationname} → {Hp.end.Information.stationname}";
            label3.Text = Hp.selDate.Add(Hp.selTime).ToString("yyyy년 MM월 dd일(ddd) HH:mm");
            Map map = new Map() { Division = Hp.division };
            int edge = map.GetRoute(Hp.start, Hp.end).Count - 1;
            price = edge * 5000;

            if (Hp.selDate.DayOfWeek == DayOfWeek.Sunday || Hp.selDate.DayOfWeek == DayOfWeek.Saturday) price = (int)(price * 1.2);

            int age = Hp.Getage(Hp.user.u_birth.Value);
            if (age < 12 || age >= 65) price = (int)(price * 0.5);
            else if (age < 19) price = (int)(price * 0.8);


            using (var db = new Model.ITTRAINEntities())
            {
                TimeSpan start = Hp.selTime;
                while (start < Hp.division.d_eTime)
                {
                    TimeSpan end = start.Add(TimeSpan.FromMinutes(edge * 20));

                    int rS = db.Reservation.Where(x => x.r_start == Hp.start.s_no && x.r_end == Hp.end.s_no && x.r_car <= Hp.division.d_carS && x.Station1.s_code / 10000 == Hp.division.d_no && x.Station.s_code / 10000 == Hp.division.d_no && x.r_date == Hp.selDate && x.r_time == start).Count();
                    int rST = Hp.division.d_carS.Value * 21;
                    dataGridView1.Rows.Add(start.ToString("hh\\:mm"), end.ToString("hh\\:mm"), (price * 1.3).ToString("N0"), "특실", rST - rS + "/" + rST);
                    dataGridView1.Rows[dataGridView1.Rows.Count - 1].Tag = start;

                    int rN = db.Reservation.Where(x => x.r_start == Hp.start.s_no && x.r_end == Hp.end.s_no && x.r_car > Hp.division.d_carS && x.Station1.s_code / 10000 == Hp.division.d_no && x.Station.s_code / 10000 == Hp.division.d_no && x.r_date == Hp.selDate && x.r_time == start).Count();
                    int rNT = (Hp.division.d_carR.Value - Hp.division.d_carS.Value) * 40;
                    dataGridView1.Rows.Add(start.ToString("hh\\:mm"), end.ToString("hh\\:mm"), price.ToString("N0"), "일반", rNT - rN + "/" + rNT);
                    dataGridView1.Rows[dataGridView1.Rows.Count - 1].Tag = start;
                    
                    start = start.Add(Hp.division.d_interval.Value);
                }
                dataGridView1.ClearSelection();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (dataGridView1.SelectedRows.Count == 0)
            {
                msgErr("예약할 노선을 선택하세요.");
                return;
            }
            Hp.selSTime = (TimeSpan)dataGridView1.SelectedRows[0].Tag;
            bool special = dataGridView1.CurrentRow.Index % 2 == 0;
            Hp.price = (int)(special ? price *1.3 : price);
            Hp.isSpecial = special;
            showPage(new SeatSelection() { isSpecial = dataGridView1.CurrentRow.Index%2==0});
        }
    }
}
