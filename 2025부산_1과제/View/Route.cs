using _2025부산_1과제.Controls;
using _2025부산_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Metadata.Edm;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025부산_1과제.View
{
    public partial class Route : _2025부산_1과제.Template.BF
    {
        Map map;
        List<Label> labels = new List<Label>();
        List<Station> stas = new List<Station>();
        int idx = 0, sidx = -1, eidx = -1;
        bool flag;

        public Route()
        {
            InitializeComponent();
            Hp.start = Hp.end = default;
        }

        private void Route_Load(object sender, EventArgs e)
        {
            using (var db = new Model.ITTRAINEntities())
            {
                var list = db.Division.Select(x => x.d_name).ToList();
                panel1.Controls.Add(map = new Map {Division = db.Division.Find(1), Dock = DockStyle.Fill, showText = true });
                comboBox1.DataSource = list;
                map.Paint += Map_Paint;
                MaximumSize = Size;
                MinimumSize = new Size(Width / 2, Height);
                Size = MinimumSize;
                labelAdd(1);
                button4.Visible = Hp.user != null;
                button5.Visible = Hp.user != null;
                button6.Visible = Hp.user != null;
            }
        }

        private void labelAdd(int v)
        {
            labels.Clear();
            stas.Clear();
            map.Controls.Clear();
            using (var db = new Model.ITTRAINEntities())
            {
                var infos = db.Station.Where(x => x.s_code.Value / 10000 == v).GroupBy(x => x.i_no).Select(g => g.FirstOrDefault()).ToList();
                foreach (var item in infos)
                {
                    int x = item.Information.x.Value * 4;
                    int y = item.Information.y.Value * 4;
                    Label lbl = new Label()
                    {
                        Tag = labels.Count,
                        AutoSize = true,
                        ForeColor = Color.Silver,
                        BorderStyle = BorderStyle.FixedSingle,
                        Location = new Point(x, y - 10),
                        Text = item.Information.stationname
                    };
                    lbl.MouseClick += Lbl_Click;
                    map.Controls.Add(lbl);
                    labels.Add(lbl);
                    this.stas.Add(item);
                }
            }
        }

        private void Lbl_Click(object sender, MouseEventArgs e)
        {
            Label lbl = sender as Label;
            if (e.Button == MouseButtons.Left)
            {
                if (Size != MaximumSize)
                {
                    plusSize();
                }
                idx = (int)lbl.Tag;
                selectLabel();
            }
            else if (e.Button == MouseButtons.Right)
            {
                if ((int)lbl.Tag == sidx || (int)lbl.Tag == eidx)
                {
                    flag = (int)lbl.Tag == sidx;
                    contextMenuStrip1.Show();
                }
            }
        }

        private void selectLabel()
        {
            using (var db = new Model.ITTRAINEntities())
            {
                var sta = stas[idx];
                db.Station.Attach(sta);
                var info = sta.Information;
                string path = "";
                if (File.Exists("./datafiles/station/" + info.i_no + ".jpg"))
                {
                    path = "station/" + info.i_no + ".jpg";
                }
                else
                {
                    path = "station/" + info.i_no + ".jpeg";
                }
                pictureBox1.Image = GetImage(path);
                label2.Text = info.stationname;
                label4.Text = info.address;
                foreach (var item in labels)
                {
                    item.ForeColor = Color.Silver;
                    item.BackColor = Color.White;
                }
                labels[idx].ForeColor = Color.Red;
                button1.Enabled = idx != 0;
                button2.Enabled = idx != stas.Count - 1;
                LabelHighlight();
            }
        }

        private async void plusSize()
        {
            int dir = Size == MaximumSize ? -3 : 3;
            if (dir > 0)
            {
                while (Width != MaximumSize.Width)
                {
                    await Task.Delay(1);
                    Width += dir;
                }
                panel2.Visible = true;
            }
            else
            {
                while (Width != MinimumSize.Width)
                {
                    await Task.Delay(1);
                    Width += dir;
                }
                panel2.Visible = false;
            }
        }

        private void Map_Paint(object sender, PaintEventArgs e)
        {
            var g = e.Graphics;
            using (var pen = new Pen(map.c[comboBox1.SelectedIndex], 2))
            using (var brush = new SolidBrush(map.c[comboBox1.SelectedIndex]))
            using (var db = new Model.ITTRAINEntities())
            {
                for (global::System.Int32 i = 0; i < map.connection.Count; i++)
                {
                    var (start, end) = map.connection[i];
                    db.Station.Attach(start);
                    db.Station.Attach(end);
                    int sx = start.Information.x.Value * 4;
                    int sy = start.Information.y.Value * 4;
                    int dx = end.Information.x.Value * 4;
                    int dy = end.Information.y.Value * 4;
                    g.DrawLine(pen, sx, sy, dx, dy);
                    g.FillEllipse(brush, sx - 4, sy - 4, 8, 8);
                    g.FillEllipse(brush, dx - 4, dy - 4, 8, 8);
                }
            }
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            using (var db = new Model.ITTRAINEntities())
            {
                map.Division = db.Division.Find(comboBox1.SelectedIndex + 1);
                map.Invalidate();
                labelAdd(comboBox1.SelectedIndex + 1);
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            plusSize();

        }

        private void 취소ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (flag)
            {
                sidx = -1;
            }
            else
            {
                sidx = -1;
            }
            LabelHighlight();
        }

        private void LabelHighlight()
        {
            if (sidx != -1)
            {
                labels[sidx].ForeColor = Color.White;
                labels[sidx].BackColor = Color.Red;
            }
            if(eidx != -1) 
            {
                labels[eidx].ForeColor = Color.Silver;
                labels[eidx].BackColor = Color.Yellow;
            }
            button4.Enabled = sidx != -1 && eidx !=-1;
        }

        private void button5_Click(object sender, EventArgs e)
        {
            sidx = idx;
            selectLabel();
        }

        private void button6_Click(object sender, EventArgs e)
        {
            eidx = idx;
            selectLabel();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            Hp.start = stas[sidx];
            Hp.end = stas[eidx];
            using (var db= new Model.ITTRAINEntities())
            {
                Hp.division = db.Division.Find(comboBox1.SelectedIndex + 1);
            }
            showPage(new TimeSelect());
        }

        private void Route_FormClosed(object sender, FormClosedEventArgs e)
        {
            
        }

        private void button1_Click(object sender, EventArgs e)
        {
            idx--;
            selectLabel();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            idx++;
            selectLabel();
        }
    }
}
