using _2025부산_1과제.Controls;
using _2025부산_1과제.Model;
using _2025부산_1과제.Template;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025부산_1과제.View
{
    public partial class SeatSelection : BF
    {
        public bool isSpecial { get; set; }
        int carno, selCarno;
        string selSeat;
        List<SeatControl> controlList = new List<SeatControl>();
        Reservation r;

        public SeatSelection(int carno, string seat, Reservation r = default, bool edit = default) : this()
        {
            selCarno = carno;
            selSeat = seat;
            this.r = r;
            seatLender();
            button3.Enabled = edit;
            textBox1.Text = carno + "호차 : " + seat;
        }

        public SeatSelection()
        {
            InitializeComponent();
        }

        private void SeatSelection_Load(object sender, EventArgs e)
        {
            label1.Text = "좌석선택(" + (isSpecial ? "특실" : "일반실") + ")";
            carno = isSpecial ? 1 : Hp.division.d_carR.Value - Hp.division.d_carS.Value;
            int mod = isSpecial ? 7 : 10;
            for (int i = 0; i < (isSpecial ? 21 : 40); i++)
            {
                int row = i / mod;
                int col = i % mod;

                string seat = (char)('A' + row) + $"{col + 1:D2}";
                SeatControl control = new SeatControl();
                control.label1.Text = seat;
                control.label1.MouseClick += Control_MouseClick;

                if (row > 1) row++;
                tableLayoutPanel1.Controls.Add(control, col, row);
                controlList.Add(control);
            }
            if (isSpecial)
            {
                tableLayoutPanel1.RowCount = 4;
                tableLayoutPanel1.ColumnCount = 7;
            }
            seatLender();
            buttonRender();
        }

        private void Control_MouseClick(object sender, MouseEventArgs e)
        {
            selSeat = (sender as Label).Text;
            if ((sender as Label).ForeColor == Color.Silver) return;
            selCarno = carno;
            seatLender();
            textBox1.Text = selCarno + "호차: " + selSeat;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            if (selSeat != default && selCarno != default)
            {
                if (r != default)
                {
                    using (var db = new Model.ITTRAINEntities())
                    {
                        var reser =  db.Reservation.Find(r.r_no);
                        reser.r_seat = selSeat;
                        db.SaveChanges();
                        Close();
                    }
                }
                else
                {
                    Hp.seat = selSeat;
                    Hp.carNo = selCarno;
                    showPage(new PaymentFom());
                }
            }

        }

        private void button1_Click(object sender, EventArgs e)
        {
            carno++;
            buttonRender();
            seatLender();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            carno--;
            buttonRender();
            seatLender();
        }

        private void buttonRender()
        {
            int min = isSpecial ? 1 : Hp.division.d_carR.Value - Hp.division.d_carS.Value;
            int max = isSpecial ? Hp.division.d_carS.Value : Hp.division.d_carR.Value;
            button2.Visible = carno != min;
            button1.Visible = max != carno;
            button2.Text = carno - 1 + "호차";
            button1.Text = carno + 1 + "호차";
        }

        private void seatLender()
        {
            using (var db = new Model.ITTRAINEntities())
            {
                foreach (var item in controlList)
                {
                    if (db.Reservation.Where(x => x.Station.s_no == Hp.start.s_no && x.Station1.s_no == Hp.end.s_no && Hp.selDate == x.r_date && x.r_time == Hp.selTime && x.r_seat == item.label1.Text && x.r_car == carno).Any())
                    {
                        item.BackgroundImage = Properties.Resources.seat0;
                        item.label1.ForeColor = Color.Silver;
                    }
                    else if (item.label1.Text == selSeat && carno == selCarno)
                    {
                        item.BackgroundImage = Properties.Resources.seat2;
                        item.label1.ForeColor = Color.Red;
                    }
                    else
                    {
                        item.BackgroundImage = Properties.Resources.seat1;
                        item.label1.ForeColor = Color.Gold;
                    }
                }
                label2.Text = carno + "호차";

            }
        }
    }
}
