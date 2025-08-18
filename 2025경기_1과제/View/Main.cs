using _2025경기_1과제.UserControls;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Metadata.Edm;
using System.Diagnostics;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace _2025경기_1과제.View
{
    public partial class Main : _2025경기_1과제.Template.BF
    {
        FlowLayoutPanel[] moviePanels = new FlowLayoutPanel[6];
        public Main()
        {
            InitializeComponent();
        }

        private void panel1_Paint(object sender, PaintEventArgs e)
        {
            using (var path = new GraphicsPath())
            {
                path.StartFigure();
                path.AddEllipse(panel1.ClientRectangle);
                panel1.Region = new Region(path);
            }
        }

        private void Main_Load(object sender, EventArgs e)
        {
            label3.Text = Hp.user.name + " 님";


            for (int i = 0; i < 6; i++)
            {
                string c = (char)('A' + i) + "관";
                Label lbl = new Label()
                {
                    Text = c,
                    TextAlign = ContentAlignment.MiddleCenter,
                    Dock = DockStyle.Fill,
                };
                Label lbl2 = new Label()
                {
                    Text = c,
                    TextAlign = ContentAlignment.MiddleCenter,
                    Dock = DockStyle.Fill,
                };
                tableLayoutPanel1.Controls.Add(lbl);
                tableLayoutPanel2.Controls.Add(lbl2);
            }
            for (int i = 0; i < 6; i++)
            {
                moviePanels[i] = new FlowLayoutPanel
                {
                    AutoSize = true,
                    Dock = DockStyle.Fill,
                    AutoSizeMode = AutoSizeMode.GrowAndShrink,
                };
                tableLayoutPanel4.Controls.Add(moviePanels[i]);
            }
            using (var db = new Model.SkillCinemaEntities())
            {
                var g = db.genre.Select(x => x.gname).ToArray();
                comboBox1.Items.AddRange(g);
            }

            areaA();

        }

        private void areaB()
        {
            using (var db = new Model.SkillCinemaEntities())
            {
                var input1 = textBox1.Text.Replace(" ", "");
                var input2 = textBox2.Text.Replace(" ", "");
                var list = db.movie.AsEnumerable().Where(x =>
                {
                    var mname = x.mname.Replace(" ", "");
                    var actors = x.actor.Replace(" ", "").Split(',');
                    return (comboBox1.SelectedIndex+1==0?true:x.gno == comboBox1.SelectedIndex+1)&&(input2==""? true : actors.Any(y => y.Contains(input2)))&&(input1==""? true : mname.Contains(input1));
                }).OrderBy(x=>x.mno).ToList();

                Size s = new Size(tableLayoutPanel3.Width / 7, tableLayoutPanel3.Height);
                foreach (var item in moviePanels)
                {
                    item.Controls.Clear();
                }

                foreach (var item in list)
                {
                    Console.WriteLine(1);
                    int idx = item.tno - 1;
                    MainControl control= new MainControl() { Movie= item, Size = s};
                    moviePanels[idx].Controls.Add(control);
                }
            }
        }

        private void areaA()
        {
            using (var db = new Model.SkillCinemaEntities())
            {
                DateTime date = DateTime.Now.Date;
                var movieList = db.movie.Where(x => x.sdate <= date && x.edate >= date).ToList();
                foreach (var movie in movieList)
                {
                    int col = movie.tno - 1;
                    MainControl control = new MainControl() { Movie = movie };
                    tableLayoutPanel3.Controls.Add(control, col, 0);
                }
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            areaB();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            ShowPage(new View.Mypage());
        }
    }
}
