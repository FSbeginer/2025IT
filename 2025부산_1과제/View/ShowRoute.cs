using _2025부산_1과제.Controls;
using _2025부산_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025부산_1과제.View
{
    public partial class ShowRoute : _2025부산_1과제.Template.BF
    {
        Map map;
        private List<Information> route;
        List<Label> labels = new List<Label>();

        Model.Reservation reservation;
        int current;
        private Timer timer;

        public ShowRoute(Reservation rer)
        {
            InitializeComponent();
            reservation = rer;
        }

        private void ShowRoute_Load(object sender, EventArgs e)
        {
            using (var db = new Model.ITTRAINEntities())
            {
                var r = db.Reservation.Find(reservation.r_no);
                map = new Map() { Division = db.Division.Find(r.Station.s_code/10000), Dock = DockStyle.Fill};
                panel1.Controls.Add(map);
                route = map.GetRoute(r.Station, r.Station1).Select(x=>db.Information.Find(x)).ToList();
                TimeSpan time = TimeSpan.FromMinutes(20*(route.Count-1));
                label2.Text = "총소요시간 : " + time.Hours + "시간 " + time.Minutes + "분";
                map.Paint += Map_Paint;

                for (global::System.Int32 i = 0; i < route.Count; i++)
                {
                    Label lbl = new Label()
                    {
                        AutoSize = true,
                        Text = r.r_time.Value.Add(TimeSpan.FromMinutes(20 * i)).ToString("hh\\:mm") + " " + route[i].stationname
                    };
                    flowLayoutPanel1.Controls.Add(lbl);
                    labels.Add(lbl);
                }
                Label start = new Label()
                {
                    AutoSize = true,
                    Location = new Point(r.Station.Information.x.Value * 4, r.Station.Information.y.Value * 4 - 30),
                    BackColor = Color.Red,
                    BorderStyle = BorderStyle.FixedSingle,
                    ForeColor = Color.Gray,
                    Text = r.Station.Information.stationname
                };
                Label end = new Label()
                {
                    AutoSize = true,
                    Location = new Point(r.Station1.Information.x.Value * 4, r.Station1.Information.y.Value * 4 - 30),
                    BackColor = Color.Yellow,
                    BorderStyle = BorderStyle.FixedSingle,
                    ForeColor = Color.Gray,
                    Text = r.Station1.Information.stationname
                };
                map.Controls.Add(start);
                map.Controls.Add(end);

                labels[0].ForeColor = Color.Red;
                timer = new Timer() { Interval = 1000};
                timer.Tick += Timer_Tick;
                timer.Start();    
            }
        }


        private void Timer_Tick(object sender, EventArgs e)
        {
            current++;
            labels[current].ForeColor = Color.Red;
            labels[current - 1].ForeColor = Color.DodgerBlue;
            map.Invalidate();
            if(current == route.Count-1) timer.Stop();
        }

        private void Map_Paint(object sender, PaintEventArgs e)
        {
            var g = e.Graphics;
            using (var pen1 = new Pen(Color.DodgerBlue, 2))
            using (var pen2 = new Pen(Color.Red, 2))
            using (var pen3 = new Pen(Color.Yellow, 2))
            {
                
                int next = Math.Min(current + 1, route.Count-1);

                int sx = route[current].x.Value * 4;
                int sy = route[current].y.Value * 4;
                int ex = route[next].x.Value * 4;
                int ey = route[next].y.Value * 4;
                g.DrawLine(pen1, sx, sy, ex, ey);

                for (global::System.Int32 i = 0; i <= current -1; i++)
                {
                    sx = route[i].x.Value * 4;
                    sy = route[i].y.Value * 4;
                    ex = route[i+1].x.Value * 4;
                    ey = route[i+1].y.Value * 4;
                    g.DrawLine(pen2, sx, sy, ex, ey);
                }
                for (global::System.Int32 i = next; i <= route.Count-2; i++)
                {
                    sx = route[i].x.Value * 4;
                    sy = route[i].y.Value * 4;
                    ex = route[i + 1].x.Value * 4;
                    ey = route[i + 1].y.Value * 4;
                    g.DrawLine(pen3, sx, sy, ex, ey);
                }

                foreach (var item in route)
                {
                    int x = item.x.Value * 4;
                    int y = item.y.Value * 4;
                    g.FillEllipse(Brushes.Red, x-4,y-4,8,8);
                }
            }
        }
    }
}
