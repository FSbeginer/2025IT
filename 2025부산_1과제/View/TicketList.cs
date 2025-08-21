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
    public partial class TicketList : BF
    {
        List<Reservation> list;
        public TicketList()
        {
            InitializeComponent();
        }

        private void TicketList_Load(object sender, EventArgs e)
        {
            LoadDatagridView();
        }

        private void LoadDatagridView()
        {
            dataGridView1.Rows.Clear();
            using (var db= new Model.ITTRAINEntities())
            {
                list = db.Reservation.AsEnumerable().Where(x => x.u_no == Hp.user.u_no && DateTime.Now.Date < x.r_date).ToList();

                foreach (var item in list)
                {
                    dataGridView1.Rows.Add(Hp.getReservationString(item, item.Station.s_code+""), item.r_date.Value.ToString("yyyy-MM-dd"), item.Station.Information.stationname, item.Station1.Information.stationname, item.r_time.Value.ToString("hh\\:mm"), item.r_car, item.r_seat);
                }
                dataGridView1.ClearSelection();
            }
            label3.Text = "보유승차권(" + list.Count + ")";
            label2.Text = "이름 : "+Hp.user.u_name+"("+Hp.user.u_id+")";
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void button3_Click(object sender, EventArgs e)
        {
            if(dataGridView1.SelectedRows.Count == 0)
            {
                msgErr("예약 내역을 선택하세요.");
                return;
            }
            using (var db = new Model.ITTRAINEntities())
            {
                var r = list[dataGridView1.CurrentRow.Index];
                var reser = db.Reservation.Find(r.r_no);
                db.Reservation.Remove(reser);
                db.SaveChanges();
            }
            LoadDatagridView();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (dataGridView1.SelectedRows.Count == 0)
            {
                msgErr("예약 내역을 선택하세요.");
                return;
            }
            //var r = list[dataGridView1.CurrentRow.Index];
            //using (var db = new Model.ITTRAINEntities())
            //{
            //    var reser  = db.Reservation.Find(r.r_no);
            //    var divi = db.Division.Find(r.Station.s_code.Value/10000-1);
            //    var sels = new View.SeatSelection(reser.r_car.Value, reser.r_seat, reser, true) { isSpecial = divi.d_carS <= reser.r_car };
            //    sels.FormClosed += (s, e2) =>
            //    {
            //        LoadDatagridView();
            //    };

            //    showPage(sels);
            //}

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (dataGridView1.SelectedRows.Count == 0)
            {
                msgErr("예약 내역을 선택하세요.");
                return;
            }
            //var r = list[dataGridView1.CurrentRow.Index];
            //showPage(new View.PaymentFom(true, r));
        }
    }
}
