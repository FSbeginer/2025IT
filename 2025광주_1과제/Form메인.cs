using _2025광주_1과제.Controls;
using _2025광주_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025광주_1과제
{
    public partial class Form메인 : BF
    {
        PictureBox[] pic = new PictureBox[10];
        Label[] labels = new Label[10];
        int idx = 0;
        FlowLayoutPanel searchPanel;

        public Form메인()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            placeHolder1.Msg = "기업명을 입력하세요.";
            placeHolder1.textBox1.Controls.Add(label2);
            placeHolder1.textBox1.Click += TextBox1_Click;
            placeHolder1.textBox1.TextChanged += TextBox1_TextChanged;
            label2.Dock = DockStyle.Right;

            AreaA();
            AreaB();

            searchPanel = new FlowLayoutPanel()
            {
                Left = placeHolder1.Left,
                Top = placeHolder1.Bottom,
                MaximumSize = new Size(placeHolder1.Width, 0),
                BorderStyle = BorderStyle.FixedSingle,
                AutoSize = true,
                Visible = false,
            };
            Controls.Add(searchPanel);
            searchPanel.BringToFront();
        }

        private void TextBox1_TextChanged(object sender, EventArgs e)
        {
            addHistory();
        }

        private void TextBox1_Click(object sender, EventArgs e)
        {
            if (Hp.user == null)
            {
                Hp.msgErr("로그인을 하지 않았습니다.");
                ActiveControl = null;
            }
        }

        private void AreaB()
        {
            using (var db = new Model.placementEntities())
            {
                var list = db.jobposting.AsEnumerable().OrderByDescending(x => x.write_date).Take(10).Select(x => x.company).ToList();
                for (int i = 0; i < 10; i++)
                {
                    labels[i] = new Label()
                    {
                        TextAlign = ContentAlignment.MiddleCenter,
                        Text = $"{i + 1} {list[i].c_name}",
                        Location = new Point(0, panel1.Height * i),
                        Size = panel1.ClientSize,
                        BorderStyle = BorderStyle.FixedSingle,
                        Tag = list[i],
                        Margin = new Padding(0),
                    };
                    labels[i].Click += Form메인_Click;
                    panel1.Controls.Add(labels[i]);
                    labels[i].BringToFront();
                }
            }
            ani2();
        }

        private void Form메인_Click(object sender, EventArgs e)
        {
            showPage(new View.Form채용공고());
        }

        private async void ani2()
        {
            while (true)
            {
                await Task.Delay(750);
                for (int i = 0; i < panel1.Height; i++)
                {
                    for (int j = 1; j <= 9; j++)
                    {
                        int moveIdx = (idx + j) % 10;
                        labels[moveIdx].Top += -1;
                    }
                    await Task.Delay(1);
                }

                labels[idx].Top = panel1.Height * 9;
                labels[idx].BringToFront();
                idx = ++idx % 10;

            }
        }

        private void AreaA()
        {
            int w = (panel4.Width - 40) / 4;

            using (var db = new Model.placementEntities())
            {
                var list = db.employment.GroupBy(x => x.c_no).OrderByDescending(x => x.Count()).ThenBy(x => x.Key).Take(10).Select(g => g.Select(x => x.company).FirstOrDefault()).ToList();

                for (int i = 0; i < 10; i++)
                {
                    string path = list[i].c_no == 4 ? "img.png" : list[i].c_no + ".png";
                    pic[i] = new PictureBox
                    {
                        Size = new Size(w, w),
                        SizeMode = PictureBoxSizeMode.StretchImage,
                        Margin = new Padding(5),
                        Location = new Point((w + 10) * i, 0),
                        Image = Hp.GetImage("company/" + path),
                        Tag = list[i]
                    };
                    pic[i].Region = Hp.GetRoundRegion(pic[i].ClientRectangle);
                    pic[i].MouseClick += FormMain_MouseClick;
                    toolTip1.SetToolTip(pic[i], "기업명: " + list[i].c_name + " | 기업의 규모: " + Hp.GetInfo(list[i].c_information));
                    panel4.Controls.Add(pic[i]);
                }

                ani();
            }
        }

        private void FormMain_MouseClick(object sender, MouseEventArgs e)
        {
            var pic = sender as PictureBox;
            showPage(new View.Form캡차(pic.Tag as Model.company));
        }

        private async void ani()
        {
            int w = (panel4.Width - 40) / 4;
            while (true)
            {
                await Task.Delay(4000);

                for (global::System.Int32 i = 0; i < panel4.Width; i++)
                {
                    foreach (var item in pic)
                    {
                        item.Left -= 1;
                        if (item.Left <= -(w + 10)) item.Left = (w + 10) * 9;
                    }
                    await Task.Delay(1);
                }
            }
        }

        private void label2_Click(object sender, EventArgs e)
        {
            placeHolder1.textBox1.Text = "";
        }

        private void label3_Click(object sender, EventArgs e)
        {
            if (Hp.user == null)
            {
                Hp.msgErr("로그인을 하지 않았습니다.");
                var log = new View.Form로그인();
                log.FormClosed += Log_FormClosed;
                showPage(log);
            }
            else
            {
                showPage(new View.Form신입인턴());
            }
        }

        private void Log_FormClosed(object sender, FormClosedEventArgs e)
        {
            UpdateForm();
        }

        public override void UpdateForm()
        {
            panel3.Controls.Clear();
            if (Hp.user != null)
            {
                panel3.Controls.Add(new Control로그인());
            }
            else
            {
                panel3.Controls.Add(label5);
            }
        }

        private void label4_Click(object sender, EventArgs e)
        {
            if (Hp.user == null)
            {
                Hp.msgErr("로그인을 하지 않았습니다.");
                var log = new View.Form로그인();
                log.FormClosed += Log_FormClosed;
                showPage(log);
            }
            else
            {
                showPage(new View.Form소개서());
            }
        }

        private void placeHolder1_Enter(object sender, EventArgs e)
        {
            if (Hp.user == null) return;
            addHistory();
            searchPanel.Visible = true;
        }

        private void addHistory()
        {
            searchPanel.Controls.Clear();
            using (var db = new Model.placementEntities())
            {
                if (placeHolder1.textBox1.TextLength == 0)
                {
                    var list = db.searchhistory.Where(x => x.u_no == Hp.user.u_no).OrderByDescending(x => x.sh_date).Take(5).ToList();
                    foreach (var item in list)
                    {
                        var c = new SearchHistory();
                        c.label1.Text = item.company.c_name;
                        c.label2.Click += Label2_Click;
                        c.label2.Tag = item;
                        searchPanel.Controls.Add(c);
                    }
                }
                else
                {
                    var list = db.company.AsEnumerable().Where(x => x.c_name.Contains(placeHolder1.textBox1.Text)).Take(5).ToList();
                    foreach (var item in list)
                    {
                        var c = new SearchHistory();
                        c.label1.Text = item.c_name;
                        searchPanel.Controls.Add(c);
                    }
                }
            }
        }

        private void Label2_Click(object sender, EventArgs e)
        {
            using (var db = new Model.placementEntities())
            {
                var s = db.searchhistory.Find(((sender as Label).Tag as searchhistory).sh_no);
                db.searchhistory.Remove(s);
                db.SaveChanges();
            }
            addHistory();
        }

        private void placeHolder1_Leave(object sender, EventArgs e)
        {
            searchPanel.Visible = false;
        }

        private void label5_Click(object sender, EventArgs e)
        {
            var log = new View.Form로그인();
            log.FormClosed += Log_FormClosed;
            showPage(log);
        }

        private void Form메인_Activated(object sender, EventArgs e)
        {
            ActiveControl = null;
        }
    }
}
