using _2025경기_1과제.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Common.CommandTrees;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using static System.Windows.Forms.AxHost;

namespace _2025경기_1과제.View
{
    public partial class Resgiser : _2025경기_1과제.Template.BF
    {
        public DateTime sDate { get; set; }
        public DateTime eDate { get; set; }

        public Resgiser()
        {
            InitializeComponent();
        }

        private void Resgiser_Load(object sender, EventArgs e)
        {
            using (var db = new Model.SkillCinemaEntities())
            {
                var list1 = db.genre.Select(x => x.gname).ToArray();
                comboBox1.Items.AddRange(list1);
                var list2 = db.theater.Select(x => x.div).ToArray();
                comboBox2.Items.AddRange(list2);
                var list3 = db.age.AsEnumerable().Select(x => x.age1.ToString()).ToArray();
                comboBox3.Items.AddRange(list3);

            }
        }

        private void pictureBox2_Click(object sender, EventArgs e)
        {
            var c = new Calendar();
            c.FormClosed += (s, e2) =>
            {
                if (c.clcik)
                {
                    sDate = c.sdate;
                    eDate = c.edate;
                    textBox5.Text = sDate.ToShortDateString();
                    textBox6.Text = eDate.ToShortDateString();
                }

            };
            ShowPage(c);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog()
            {
                Filter = "JPG FIle|*.jpg",
                Multiselect = false,
            };
            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                var file = openFileDialog.FileName;
                pictureBox1.Image = Image.FromFile(file);
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (comboBox1.SelectedIndex == -1 || comboBox2.SelectedIndex == -1 || comboBox3.SelectedIndex == -1 || isNullor(textBox1, textBox2, textBox3, textBox4, textBox5, textBox6, textBox7))
            {
                msgErr("빈칸이 있습니다.");
                return;
            }
            using (var db = new Model.SkillCinemaEntities())
            {
                var title = textBox1.Text;
                if (db.movie.Select(x => x.mname.Replace(" ", "").ToUpper()).Any(x => x == title))
                {
                    msgErr("중복된 영화 제목입니다.");
                    return;
                }
                int tno = comboBox2.SelectedIndex + 1;
                if (db.movie.Where(x => x.tno == tno).Any(x => !(x.sdate > eDate || sDate > x.edate)))
                {
                    msgErr("상영관에 겹치는 일정이 있습니다.");
                    return;
                }
                try
                {
                    int rtime = int.Parse(textBox7.Text);
                    var m = new movie();
                    m.mname = textBox1.Text;
                    m.gno = comboBox1.SelectedIndex + 1;
                    m.introduction = textBox2.Text;
                    m.director = textBox3.Text;
                    m.actor = textBox4.Text;
                    m.sdate = sDate;
                    m.edate = eDate;
                    m.rtime = rtime;
                    m.tno = comboBox2.SelectedIndex + 1;
                    m.ano = comboBox3.SelectedIndex + 1;
                    db.movie.Add(m);
                    db.SaveChanges();

                    Bitmap bit = new Bitmap(pictureBox1.Image);
                    bit.Save("./datafiles/Image/" + (db.movie.Count()) + ".jpg", ImageFormat.Jpeg);
                    msgInfo("영화등록이 완료되었습니다.");

                }
                catch (Exception)
                {
                    msgErr("상영시관은 숫자로 입력하세요.");
                    return;
                }
            }
        }

        private bool isNullor(params TextBox[] textBox)
        {
            foreach (var item in textBox)
            {
                if (string.IsNullOrEmpty(item.Text))
                    return true;
            }
            return false;
        }
    }
}
