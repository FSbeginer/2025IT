using _2025광주_1과제_2.Model;
using _2025광주_1과제_2.MyControl;
using _2025광주_1과제_2.Template;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Metadata.Edm;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml;

namespace _2025광주_1과제_2
{
    public partial class A_메인 : BF
    {
        public A_메인()
        {
            InitializeComponent();
        }

        PictureBox[] pics = new PictureBox[10];
        FlowLayoutPanel searchPanel;
        private void MainForm_Load(object sender, EventArgs e)
        {
            setPics();
            threading();
            AreaB();
            threading2();
            searchPanel = new FlowLayoutPanel()
            {
                AutoSize = true,
                AutoSizeMode = AutoSizeMode.GrowAndShrink,
                Top = placeHolder1.Bottom,
                Left = placeHolder1.Left,
                BorderStyle = BorderStyle.FixedSingle,
                FlowDirection = FlowDirection.TopDown,
                Visible = false,
            };
            Controls.Add(searchPanel);
            searchPanel.BringToFront();
        }

        int top = 0;
        private async void threading2()
        {
            while (true)
            {
                await Task.Delay(750);
                int h = panel1.Height;
                while (h-- > 0)
                {
                    for (global::System.Int32 i = 1; i < 10; i++)
                    {
                        int idx = (top + i) % 10;
                        lbls[idx].Top -= 1;
                    }
                    await Task.Delay(1);
                }
                lbls[top].Top = panel1.Height * 9;
                lbls[top].BringToFront();
                top = ++top % 10;
            }
        }

        Label[] lbls = new Label[10];
        private void AreaB()
        {
            using (var db = new Model.placementEntities())
            {
                var list = db.jobposting.OrderByDescending(x => x.write_date).ThenBy(x => x.c_no).Select(x => x.company).Distinct().Take(10).ToList();
                int w = panel1.Width;
                int h = panel1.Height;
                for (global::System.Int32 i = 0; i < 10; i++)
                {
                    lbls[i] = new Label()
                    {
                        Size = panel1.Size,
                        Location = new Point(0, h * i),
                        BorderStyle = BorderStyle.FixedSingle,
                        Tag = db.jobposting.AsEnumerable().Where(x => x.company.c_no == list[i].c_no).OrderByDescending(x => x.write_date).FirstOrDefault(),
                        Text = i + 1 + " " + list[i].c_name,
                        TextAlign = ContentAlignment.MiddleLeft,
                    };
                    lbls[i].MouseClick += A_메인_MouseClick1;
                    panel1.Controls.Add(lbls[i]);
                    lbls[i].BringToFront();
                }
            }
        }

        private void A_메인_MouseClick1(object sender, MouseEventArgs e)
        {
            Label lbb = sender as Label;
            showPage(new View.D_채용공고() { jobposting = lbb.Tag as jobposting });
        }

        private async void threading()
        {
            while (true)
            {
                await Task.Delay(1000);

                int cnt = panel2.Width;
                while (cnt-- > 0)
                {
                    foreach (var item in pics)
                    {
                        item.Left -= 1;
                        if (item.Left == -(item.Width + 10)) item.Left = (item.Width + 10) * 9;
                    }
                    if (cnt % 3 == 0)
                        await Task.Delay(1);
                }
            }
        }

        private void setPics()
        {
            int w = (panel2.Width - 40) / 4;
            int h = panel2.Height;
            using (var db = new Model.placementEntities())
            {
                var list = db.employment.GroupBy(x => x.c_no).OrderByDescending(x => x.Count()).ThenBy(x => x.FirstOrDefault().c_no).Take(10).Select(x => x.FirstOrDefault()).ToList();
                for (int i = 0; i < 10; i++)
                {
                    string path = list[i].c_no == 4 ? "img.png" : list[i].c_no + ".png";
                    pics[i] = new PictureBox
                    {
                        Size = new Size(w, w),
                        Location = new Point((w + 10) * i, 0),
                        SizeMode = PictureBoxSizeMode.StretchImage,
                        Image = GetImage("company/" + path),
                        Tag = "company/" + path
                    };
                    pics[i].Region = Hp.GetRegion(pics[i].ClientRectangle);
                    pics[i].MouseClick += A_메인_MouseClick;
                    panel2.Controls.Add(pics[i]);
                    toolTip1.SetToolTip(pics[i], "회사명: " + list[i].company.c_name + " | " + Hp.companySize(list[i].company.c_information));
                }
            }
        }

        private void A_메인_MouseClick(object sender, MouseEventArgs e)
        {
            PictureBox pic = sender as PictureBox;
            new View.캡차(GetImage(pic.Tag.ToString())).ShowDialog();
        }
        private void LoadSearchPanel()
        {
            searchPanel.Controls.Clear();
            using (var db = new Model.placementEntities())
            {
                if (string.IsNullOrWhiteSpace(placeHolder1.Text))
                {
                    var list = db.searchhistory.Where(x => x.u_no == Hp.user.u_no).OrderByDescending(x => x.sh_date).Take(5).ToList();
                    foreach (var item in list)
                    {
                        SearchControl pp = new SearchControl();
                        pp.label2.Text = item.company.c_name;
                        pp.Tag = item;
                        pp.label1.MouseClick += Label1_MouseClick;
                        pp.label2.MouseClick += Label2_MouseClick;
                        pp.ClientSize = placeHolder1.Size;
                        pp.Margin = new Padding(0);
                        searchPanel.Controls.Add(pp);
                    }
                }
                else
                {
                    var list = db.company.Where(x => x.c_name.Contains(placeHolder1.Text)).Take(5).ToList();
                    foreach (var item in list)
                    {
                        Label pp = new Label();
                        pp.Text = item.c_name;
                        pp.Tag = item;
                        pp.ClientSize = placeHolder1.Size;
                        pp.MouseClick += Label2_MouseClick;
                        pp.Margin = new Padding(0);
                        searchPanel.Controls.Add(pp);
                    }
                }
            }
        }

        private void Label2_MouseClick(object sender, MouseEventArgs e)
        {
            placeHolder1.Text = (sender as Label).Text;
        }

        private void Label1_MouseClick(object sender, MouseEventArgs e)
        {
            Label lbl = sender as Label;

            using (var db = new Model.placementEntities())
            {
                searchhistory his = lbl.Tag as searchhistory;
                searchhistory sh = db.searchhistory.Find(his.sh_no);
                db.searchhistory.Remove(sh);
                db.SaveChanges();
            }
        }

        private void placeHolder1_Leave(object sender, EventArgs e)
        {
            searchPanel.Visible = false;
        }

        private void placeHolder1_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                if (string.IsNullOrWhiteSpace(placeHolder1.Text))
                {
                    msgErr("빈칸이 있습니다.");
                    return;
                }
                using (var db = new Model.placementEntities())
                {
                    var job = db.jobposting.Where(x => x.company.c_name.Contains(placeHolder1.Text)).OrderByDescending(x=>x.write_date).FirstOrDefault();
                    searchhistory sh = new searchhistory();
                    sh.u_no = Hp.user.u_no;
                    sh.c_no = job.c_no;
                    sh.sh_date = DateTime.Today;
                    db.searchhistory.Add(sh);
                    db.SaveChanges();
                    showPage(new View.D_채용공고 { jobposting = job });
                }
            }
        }

        private void label4_MouseClick(object sender, MouseEventArgs e)
        {
            if (Hp.user == null)
            {
                showPage(new View.B_로그인());
            }
        }
        public override void updateForm()
        {
            if (Hp.user != null)
            {
                pictureBox1.Image = GetImage("user/" + Hp.user.u_no + ".png");
                label4.Text = Hp.user.u_name;
            }
        }

        private void placeHolder1_MouseClick(object sender, MouseEventArgs e)
        {
            if (Hp.user == null)
            {
                msgErr("로그인을 하지 않았습니다.");
                ActiveControl = null;
            }
            else
            {
                searchPanel.Visible = true;
                LoadSearchPanel();
            }
        }

        private void label2_Click(object sender, EventArgs e)
        {
            if (Hp.user == null)
            {
                msgErr("로그인을 하지 않았습니다.");
                showPage(new View.B_로그인());
            }
            else
            {
                showPage(new View.C_신입인턴());
            }
        }

        private void label3_Click(object sender, EventArgs e)
        {
            if (Hp.user == null)
            {
                msgErr("로그인을 하지 않았습니다.");
                showPage(new View.B_로그인());
            }
            else
            {
                showPage(new View.E_소개서());
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            if (Hp.user != null)
            {
                showPage(new View.G_마이페이지());
            }
        }

        private void label5_Click(object sender, EventArgs e)
        {
            placeHolder1.Clear();
        }

        private void placeHolder1_TextChanged(object sender, EventArgs e)
        {
            LoadSearchPanel();
        }
    }
}
