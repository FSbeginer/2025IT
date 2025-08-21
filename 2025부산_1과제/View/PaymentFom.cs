using _2025부산_1과제.Controls;
using _2025부산_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025부산_1과제.View
{
    public partial class PaymentFom : _2025부산_1과제.Template.BF
    {
        Reservation r;
        int useMileage;
        bool skip;

        public PaymentFom(bool skip, Reservation r) : this()
        {
            this.r = r;
            this.skip = skip;
            LoadView3();
        }
        public PaymentFom()
        {
            InitializeComponent();
        }
        private void PaymentFom_Load(object sender, EventArgs e)
        {
            label3.Text = Hp.selDate.ToString("yyyy년 MM월 dd일(ddd)");
            label2.Text = Hp.start.Information.stationname;
            label4.Text = Hp.end.Information.stationname;
            label5.Text = Hp.selSTime.ToString("hh\\:mm");
            var m = new Map { Division = Hp.division };
            int edge = m.GetRoute(Hp.start, Hp.end).Count - 1;
            label6.Text = Hp.selSTime.Add(TimeSpan.FromMinutes(20 * edge)).ToString("hh\\:mm");
            label7.Text = Hp.carNo + "호차 " + Hp.seat;
            string week = Hp.selDate.DayOfWeek == DayOfWeek.Sunday || Hp.selDate.DayOfWeek == DayOfWeek.Saturday ? "주말" : "평일";
            lblPrice.Text = Hp.price.ToString("N0") + "(" + (Hp.isSpecial ? "특실" : "일반") + "/" + week + ")";
            label16.Text = lblPrice.Text;
            lblMilage.Text = Hp.user.u_mileage.Value.ToString("N0");
            lblTot.Text = Hp.price.ToString("N0");

        }

        private void button3_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                useMileage = int.Parse(textBox1.Text);
                lblTot.Text = (Hp.price - useMileage).ToString("N0");
            }
            catch (Exception ex)
            {
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            useMileage = Hp.user.u_mileage.Value > Hp.price ? Hp.price : Hp.user.u_mileage.Value;
            lblTot.Text = (Hp.price - useMileage).ToString("N0");
        }

        private void button4_Click(object sender, EventArgs e)
        {
            if (tapHeaderLess1.SelectedIndex == 2)
            {
                if (!skip)
                {
                    using (var db = new Model.ITTRAINEntities())
                    {
                        db.User.Attach(Hp.user);
                        Hp.user.u_mileage += (int?)(Hp.price * 0.1 / 1000 * 1000);
                        Hp.user.u_mileage -= useMileage;
                        db.Reservation.Add(r);
                        db.SaveChanges();
                    }
                }
                msgInfo("예약이 완료되었습니다.");
                Hp.reset();
                showPage(new ShowRoute(r));
            }
            else if (tapHeaderLess1.SelectedIndex == 1)
            {
                var txts = new[] { textBox2, textBox3, textBox4, textBox5 };
                if (txts.Any(x => x.TextLength != 4) || txts.Any(x => !int.TryParse(x.Text, out _)))
                {
                    msgErr("카드 숫자 각 4자리를 확인하세요.");
                    return;
                }
                if (!DateTime.TryParse(textBox6.Text.Substring(2).Length + "-" + textBox6.Text.Substring(0, 2), out _))
                {
                    msgErr("유효기간형식을 확인하세요.");
                    return;
                }
                if (textBox7.Text != Hp.user.u_pw)
                {
                    msgErr("비밀번호를 확인하세요.");
                    return;
                }
                tapHeaderLess1.SelectedIndex = tapHeaderLess1.SelectedIndex + 1;
                button4.Text = "확인";
                LoadView3();
            }
            else
            {
                tapHeaderLess1.SelectedIndex = tapHeaderLess1.SelectedIndex + 1;
            }
        }

        private void LoadView3()
        {

            if (!skip)
            {
                dataGridView1.Rows.Add(Hp.carNo + "호차", Hp.seat, Hp.isSpecial ? "특실" : "일반", Properties.Resources.qrcode);
                r = new Reservation();
                r.u_no = Hp.user.u_no;
                r.r_date = Hp.selDate;
                r.r_start = Hp.start.s_no;
                r.r_end = Hp.end.s_no;
                r.r_time = Hp.selSTime;
                r.r_car = Hp.carNo;
                r.r_seat = Hp.seat;
                dataGridView1.ClearSelection();
            }
            label17.Text = Hp.getReservationString(r, Hp.start.s_code+"");
        }

        private void textBox2_Enter(object sender, EventArgs e)
        {
            var t = sender as TextBox;
            t.BackColor = Color.Yellow;
        }

        private void textBox2_Leave(object sender, EventArgs e)
        {
            var t = sender as TextBox;
            t.BackColor = Color.White;
        }

        private void textBox3_Click(object sender, EventArgs e)
        {
            var c = new KeyPad { textbox = sender as TextBox };
            c.ShowDialog();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            showPage(new SeatSelection(Hp.carNo, Hp.seat) { isSpecial = Hp.isSpecial });
        }

        private void textBox6_TextChanged(object sender, EventArgs e)
        {
            label19.Visible = textBox6.TextLength == 0;
        }
    }
    class TapHeaderLess : TabControl
    {
        protected override void WndProc(ref Message m)
        {
            if (m.Msg == 0x1328)
            {
                m.Result = new IntPtr(1);
                return;
            }
            base.WndProc(ref m);
        }
    }

}
