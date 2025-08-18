using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Imaging;
using System.Text;
using System.Windows.Forms;

namespace _2025경기_1과제.View
{
    public partial class AdminMain : _2025경기_1과제.Template.BF
    {
        public AdminMain()
        {
            InitializeComponent();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            ShowPage(new View.Resgiser());
        }

        private void button2_Click(object sender, EventArgs e)
        {
            ShowPage(new View.SearchMoviecs());
        }

        List<List<Image>> frames = new List<List<Image>>();
        int idx, subidx;
        private void AdminMain_Load(object sender, EventArgs e)
        {
            GetGifFrame();
            Timer timer = new Timer();
            timer.Interval = 100;
            timer.Tick += Timer_Tick;
            timer.Start();
        }

        private void Timer_Tick(object sender, EventArgs e)
        {
            pictureBox1.Image = frames[idx][subidx++];
            if (frames[idx].Count <= subidx)
            {
                subidx = 0;
                idx = ++idx % 5;
            }
        }

        private void GetGifFrame()
        {
            for (int i = 0; i < 5; i++)
            {
                var list = new List<Image>();
                Image img = GetImage("홍보영상/"+(i+1)+".gif");
                FrameDimension fd = new FrameDimension(img.FrameDimensionsList[0]);
                for (global::System.Int32 j = 0; j < img.GetFrameCount(fd); j++)
                {
                    img.SelectActiveFrame(fd, j);
                    Bitmap bit = new Bitmap(img);
                    list.Add(bit);
                }
                frames.Add(list);
            }
        }
    }
}
