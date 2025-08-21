using _2025부산_1과제.Controls;
using _2025부산_1과제.Template;
using _2025부산_1과제.View;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025부산_1과제
{
    public partial class MainForm : BF
    {
        List<List<Image>> frames = new List<List<Image>>();
        Timer timer = new Timer() { Interval = 30 };
        Stopwatch Stopwatch = new Stopwatch();
        int idx, subidx;
        CircleButton[] cir = new CircleButton[5];

        public MainForm()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            UpdateForm();
            for (int i = 0; i < 5; i++)
            {
                cir[i] = new CircleButton();
            }
            timer.Tick += Timer_Tick;
            GetFrame();
        }

        private void Timer_Tick(object sender, EventArgs e)
        {
            if (frames.Count == 0) return;

            foreach (var item in cir)
            {
                item.panel1.BackColor = Color.White;
            }
            cir[idx].panel1.BackColor = Color.Navy;

            pictureBox2.Image = frames[idx][subidx++];
            subidx %= frames[idx].Count;
            if (Stopwatch.ElapsedMilliseconds >= 3000)
            {
                idx = ++idx % frames.Count;
                subidx = 0;
                Stopwatch.Restart();
            }
        }

        private void GetFrame()
        {
            timer.Stop();
            Stopwatch.Stop();
            frames.Clear();
            for (int i = 0; i < 5; i++)
            {
                if (File.Exists("./datafiles/advertisement/" + (i + 1) + ".gif"))
                {
                    using (var fs = new FileStream("./datafiles/advertisement/" + (i + 1) + ".gif", FileMode.Open,FileAccess.Read))
                    {
                        Image image = Image.FromStream(fs);
                        FrameDimension fd = new FrameDimension(image.FrameDimensionsList[0]);
                        List<Image> list = new List<Image>();
                        for (global::System.Int32 j = 0; j < image.GetFrameCount(fd); j++)
                        {
                            image.SelectActiveFrame(fd, j);
                            list.Add(new Bitmap(image));
                        }
                        frames.Add(list);

                    }
                }
            }
            flowLayoutPanel1.Controls.Clear();
            for (int i = 0; i < frames.Count; i++)
            {
                flowLayoutPanel1.Controls.Add(cir[i]);
            }
            if (Hp.isAdmin)
            {
                flowLayoutPanel1.Controls.Add(lblMinus);
                flowLayoutPanel1.Controls.Add(lblPlus);
                lblMinus.Enabled = frames.Count != 0;
                lblPlus.Enabled = frames.Count != 5;
            }
            else
            {
                flowLayoutPanel1.Controls.Add(lblStop);
            }
            idx = 0;
            subidx = 0;
            timer.Start();
            Stopwatch.Start();
        }

        public override void UpdateForm()
        {
            if (Hp.user != null)
            {
                button3.Visible = true;
                button1.Text = "로그아웃";
                button2.Text = "예매";
                button3.Text = "승차권확인";
            }
            else if (Hp.isAdmin)
            {
                button3.Visible = false;
                button1.Text = "로그아웃";
                button2.Text = "분석";
            }
            else
            {
                button3.Visible = false;
                button1.Text = "로그인";
                button2.Text = "노선검색";
            }
        }

        private void flowLayoutPanel1_Paint(object sender, PaintEventArgs e)
        {
            using (var path = new GraphicsPath())
            {
                int l = flowLayoutPanel1.Height;
                path.StartFigure();
                path.AddArc(0, 0, l, l, 90, 180);
                path.AddArc(flowLayoutPanel1.Width - l, 0, l, l, 270, 180);
                flowLayoutPanel1.Region = new Region(path);
            }
        }

        private void lblStop_Paint(object sender, PaintEventArgs e)
        {
            e.Graphics.DrawEllipse((sender as Control).Enabled ? Pens.White : Pens.Gray, 0, 0, 24, 24);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (Hp.isAdmin)
            {
                showPage(new View.Analyze());
            }
            else
            {
                showPage(new Route());
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            showPage(new View.TicketList());
        }

        private void lblStop_Click(object sender, EventArgs e)
        {
            if (timer.Enabled)
            {
                timer.Stop();
                lblStop.Text = "▶";
            }
            else
            {
                timer.Start();
                lblStop.Text = "||";
            }
        }

        private void lblMinus_Click(object sender, EventArgs e)
        {
            File.Delete("./datafiles/advertisement/" + (idx + 1) + ".gif");
            for (int i = idx + 2; i <= 5; i++)
            {
                if (File.Exists("./datafiles/advertisement/" + i + ".gif"))
                {
                    File.Move("./datafiles/advertisement/" + i + ".gif", "./datafiles/advertisement/" + (i - 1) + ".gif");
                }
            }
            GetFrame();
        }

        private void lblPlus_Click(object sender, EventArgs e)
        {
            timer.Stop();
            Stopwatch.Stop();
            OpenFileDialog openFileDialog = new OpenFileDialog()
            {
                Filter = "GIF File|*.gif",
                Multiselect = false,
            };
            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                for (global::System.Int32 i = 4; i >= idx + 1; i--)
                {
                    if (File.Exists("./datafiles/advertisement/" + i + ".gif"))
                    {
                        File.Move("./datafiles/advertisement/" + i + ".gif", "./datafiles/advertisement/" + (i + 1) + ".gif");
                    }
                }

                var file = openFileDialog.FileName;
                File.Copy(file, "./datafiles/advertisement/" + (idx + 1) + ".gif");
            }
            GetFrame();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if(Hp.user!=null || Hp.isAdmin)
            {
                Hp.user = null; 
                Hp.isAdmin = false;
                UpdateForm();
            }
            else
            {
                var l = new View.LoginForm();
                l.FormClosed += L_FormClosed;
                showPage(l);
            }
        }

        private void L_FormClosed(object sender, FormClosedEventArgs e)
        {
            UpdateForm();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }
    }
}
